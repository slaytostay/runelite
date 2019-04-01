/*
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

#version 330

#define TILE_SIZE 128
#define LOCKED_REGIONS_SIZE 36

#define FOG_SCENE_EDGE_MIN TILE_SIZE
#define FOG_SCENE_EDGE_MAX (103 * TILE_SIZE)

layout (location = 0) in ivec4 VertexPosition;
layout (location = 1) in vec4 uv;

layout(std140) uniform uniforms {
  int cameraYaw;
  int cameraPitch;
  int centerX;
  int centerY;
  int zoom;
  int cameraX;
  int cameraY;
  int cameraZ;
  ivec2 sinCosTable[2048];
};

uniform float brightness;
uniform int useFog;
uniform int fogDepth;
uniform int drawDistance;

uniform int baseX;
uniform int baseY;
uniform ivec4 lockedRegions[LOCKED_REGIONS_SIZE];

out ivec3 vPosition;
out vec4 vColor;
out float vHsl;
out vec4 vUv;
out float vFogAmount;
out float vGrayAmount;

#include hsl_to_rgb.glsl

float fogFactorLinear(const float dist, const float start, const float end) {
  return 1.0 - clamp((dist - start) / (end - start), 0.0, 1.0);
}

void main()
{
  ivec3 vertex = VertexPosition.xyz;
  int ahsl = VertexPosition.w;
  int hsl = ahsl & 0xffff;
  float a = float(ahsl >> 24 & 0xff) / 255.f;

  vec3 rgb = hslToRgb(hsl);

  vPosition = vertex;
  vColor = vec4(rgb, 1.f - a);
  vHsl = float(hsl);
  vUv = uv;

  int fogWest = max(FOG_SCENE_EDGE_MIN, cameraX - drawDistance);
  int fogEast = min(FOG_SCENE_EDGE_MAX, cameraX + drawDistance - TILE_SIZE);
  int fogSouth = max(FOG_SCENE_EDGE_MIN, cameraZ - drawDistance);
  int fogNorth = min(FOG_SCENE_EDGE_MAX, cameraZ + drawDistance - TILE_SIZE);

  // Calculate distance from the scene edge
  float fogDistance = min(min(vertex.x - fogWest, fogEast - vertex.x), min(vertex.z - fogSouth, fogNorth - vertex.z));

  vFogAmount = fogFactorLinear(fogDistance, 0, fogDepth * TILE_SIZE) * useFog;

  float gray = 1;
  for (int i = 0; i < LOCKED_REGIONS_SIZE; i++) {
    ivec4 region = lockedRegions[i];
    if (region.x == 0) { continue; }
    if (baseX < 0 || baseY < 0) {
        gray = 0;
        break;
    }
    if ((vertex.x + baseX) >= region.x && (vertex.x + baseX) <= region.z && (vertex.z + baseY) >= region.y && (vertex.z + baseY) <= region.w) {
    //if ((vertex.x + baseX) == region.x || (vertex.x + baseX) == region.z || (vertex.z + baseY) == region.y || (vertex.z + baseY) == region.w) {
      gray = 0;
      break;
    }
  }
  vGrayAmount = gray;
}
