/*
 * Copyright (c) 2017, Tyler <https://github.com/tylerthardy>
 * Copyright (c) 2018, Shaun Dreclin <shaundreclin@gmail.com>
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
package net.runelite.client.plugins.slayer;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import lombok.Getter;
import net.runelite.api.ItemID;

@Getter
public enum Task
{
	//<editor-fold desc="Enums">
	ABERRANT_SPECTRES("Aberrant spectres", ItemID.ABERRANT_SPECTRE, "Spectre"),
	ABYSSAL_DEMONS("Abyssal demons", ItemID.ABYSSAL_DEMON),
	ABYSSAL_SIRE("Abyssal Sire", ItemID.ABYSSAL_ORPHAN),
	ADAMANT_DRAGONS("Adamant dragons", ItemID.ADAMANT_DRAGON_MASK),
	ALCHEMICAL_HYDRA("Alchemical Hydra", ItemID.IKKLE_HYDRA),
	ANKOU("Ankou", ItemID.ANKOU_MASK),
	AVIANSIES("Aviansies", ItemID.ENSOULED_AVIANSIE_HEAD),
	BANSHEES("Banshees", ItemID.BANSHEE, "Twisted banshee", "Screaming banshee"),
	BARROWS_BROTHERS("Barrows Brothers", ItemID.KARILS_COIF),
	BASILISKS("Basilisks", ItemID.BASILISK),
	BATS("Bats", ItemID.GIRAL_BAT_2, "Giant bat", "Death wing", "Albino bat"),
	BEARS("Bears", ItemID.ENSOULED_BEAR_HEAD, "Bear cub", "Black bear", "Grizzly bear"),
	ENTS("Ents", ItemID.NICE_TREE, "Ent"),
	LAVA_DRAGONS("Lava Dragons", ItemID.LAVA_SCALE, "Lava dragon"),
	BIRDS("Birds", ItemID.FEATHER, "Chicken", "Undead chicken", "Penguin", "Rooster", "Duck", "Evil chicken", "Terrorbird", "Seagull", "Mounted terrorbird gnome", "Vulture", "Chompy bird"),
	BLACK_DEMONS("Black demons", ItemID.BLACK_DEMON_MASK, "Demonic gorilla"),
	BLACK_DRAGONS("Black dragons", ItemID.BLACK_DRAGON_MASK, "Baby black dragon", "Brutal black dragon"),
	BLOODVELD("Bloodveld", ItemID.BLOODVELD, "Mutated bloodveld"),
	BLUE_DRAGONS("Blue dragons", ItemID.BLUE_DRAGON_MASK, "Baby blue dragon", "Brutal blue dragon"),
	BRINE_RATS("Brine rats", ItemID.BRINE_RAT),
	BRONZE_DRAGONS("Bronze dragons", ItemID.BRONZE_DRAGON_MASK),
	CALLISTO("Callisto", ItemID.CALLISTO_CUB),
	CATABLEPON("Catablepon", ItemID.LEFT_SKULL_HALF),
	CAVE_BUGS("Cave bugs", ItemID.SWAMP_CAVE_BUG),
	CAVE_CRAWLERS("Cave crawlers", ItemID.CAVE_CRAWLER, "Chasm crawler"),
	CAVE_HORRORS("Cave horrors", ItemID.CAVE_HORROR, "Cave abomination"),
	CAVE_KRAKEN("Cave kraken", ItemID.CAVE_KRAKEN),
	CAVE_SLIMES("Cave slimes", ItemID.SWAMP_CAVE_SLIME),
	CERBERUS("Cerberus", ItemID.HELLPUPPY),
	CHAOS_ELEMENTAL("Chaos Elemental", ItemID.PET_CHAOS_ELEMENTAL),
	CHAOS_FANATIC("Chaos Fanatic", ItemID.ANCIENT_STAFF),
	COCKATRICE("Cockatrice", ItemID.COCKATRICE, "Cockathrice"),
	COWS("Cows", ItemID.COW_MASK),
	CRAWLING_HANDS("Crawling hands", ItemID.CRAWLING_HAND, "Crushing hand"),
	CRAZY_ARCHAEOLOGIST("Crazy Archaeologists", ItemID.FEDORA, "Crazy Archaeologist"),
	CROCODILES("Crocodiles", ItemID.SWAMP_LIZARD),
	DAGANNOTH("Dagannoth", ItemID.DAGANNOTH),
	DAGANNOTH_KINGS("Dagannoth Kings", ItemID.PET_DAGANNOTH_PRIME),
	DARK_BEASTS("Dark beasts", ItemID.DARK_BEAST, "Night beast"),
	DARK_WARRIORS("Dark warriors", ItemID.BLACK_MED_HELM, "Dark warrior"),
	DERANGED_ARCHAEOLOGIST("Deranged Archaeologist", ItemID.ARCHAEOLOGISTS_DIARY),
	DOGS("Dogs", ItemID.GUARD_DOG, "Jackal", "Guard dog"),
	DRAKES("Drakes", ItemID.DRAKE),
	DUST_DEVILS("Dust devils", ItemID.DUST_DEVIL, "Choke devil"),
	DWARVES("Dwarves", ItemID.DWARVEN_HELMET, "Dwarf", "Guard (Dwarf)", "Black guard (Dwarf)"),
	EARTH_WARRIORS("Earth warriors", ItemID.BRONZE_FULL_HELM_T),
	ELVES("Elves", ItemID.ELF, "Elf", "Mourner", "Elf warrior"),
	FEVER_SPIDERS("Fever spiders", ItemID.FEVER_SPIDER),
	FIRE_GIANTS("Fire giants", ItemID.FIRE_BATTLESTAFF),
	REVENANTS("Revenants", ItemID.BRACELET_OF_ETHEREUM, "Revenant imp", "Revenant goblin", "Revenant pyrefiend", "Revenant hobgoblin", "Revenant cyclops", "Revenant hellhound", "Revenant demon", "Revenant ork", "Revenant dark beast", "Revenant knight", "Revenant dragon"),
	FLESH_CRAWLERS("Fleshcrawlers", ItemID.ENSOULED_SCORPION_HEAD, "Flesh crawler"),
	FOSSIL_ISLAND_WYVERNS("Fossil island wyverns", ItemID.FOSSIL_ISLAND_WYVERN, "Ancient wyvern", "Long-tailed wyvern", "Spitting wyvern", "Taloned wyvern"),
	GARGOYLES("Gargoyles", ItemID.GARGOYLE, 9, ItemID.ROCK_HAMMER),
	GENERAL_GRAARDOR("General Graardor", ItemID.PET_GENERAL_GRAARDOR),
	GHOSTS("Ghosts", ItemID.GHOSTSPEAK_AMULET, "Tortured soul"),
	GIANT_MOLE("Giant Mole", ItemID.BABY_MOLE),
	GHOULS("Ghouls", ItemID.ZOMBIE_HEAD),
	GOBLINS("Goblins", ItemID.ENSOULED_GOBLIN_HEAD, "Cave goblin miner", "Cave goblin guard", "Guard (Goblin)"),
	GREATER_DEMONS("Greater demons", ItemID.GREATER_DEMON_MASK),
	GREEN_DRAGONS("Green dragons", ItemID.GREEN_DRAGON_MASK),
	GROTESQUE_GUARDIANS("Grotesque Guardians", ItemID.MIDNIGHT, 0, ItemID.ROCK_HAMMER, "Dusk", "Dawn"),
	HARPIE_BUG_SWARMS("Harpie bug swarms", ItemID.SWARM),
	HELLHOUNDS("Hellhounds", ItemID.HELLHOUND),
	HILL_GIANTS("Hill giants", ItemID.ENSOULED_GIANT_HEAD, "Cyclops", "Obor"),
	HOBGOBLINS("Hobgoblins", ItemID.HOBGOBLIN_GUARD),
	HYDRAS("Hydras", ItemID.HYDRA),
	ICE_GIANTS("Ice giants", ItemID.ICE_DIAMOND),
	ICE_WARRIORS("Ice warriors", ItemID.MITHRIL_FULL_HELM_T),
	ICEFIENDS("Icefiends", ItemID.ICE_DIAMOND),
	INFERNAL_MAGES("Infernal mages", ItemID.INFERNAL_MAGE, "Malevolent mage"),
	IRON_DRAGONS("Iron dragons", ItemID.IRON_DRAGON_MASK),
	JAD("TzTok-Jad", ItemID.TZREKJAD, 25250),
	JELLIES("Jellies", ItemID.JELLY, "Jelly"),
	JUNGLE_HORROR("Jungle horrors", ItemID.ENSOULED_HORROR_HEAD),
	KALPHITE("Kalphite", ItemID.KALPHITE_SOLDIER, "Kalphite worker", "Kalphite soldier", "Kalphite guardian"),
	MAMMOTHS("Mammoths", ItemID.ATTACKER_HORN, "Mammoth"),
	KALPHITE_QUEEN("Kalphite Queen", ItemID.KALPHITE_PRINCESS),
	KILLERWATTS("Killerwatts", ItemID.KILLERWATT),
	KING_BLACK_DRAGON("King Black Dragon", ItemID.PRINCE_BLACK_DRAGON),
	KRAKEN("Cave Kraken Boss", ItemID.PET_KRAKEN, "Kraken"),
	KREEARRA("Kree'arra", ItemID.PET_KREEARRA),
	KRIL_TSUTSAROTH("K'ril Tsutsaroth", ItemID.PET_KRIL_TSUTSAROTH),
	KURASK("Kurask", ItemID.KURASK),
	ROGUES("Rogues", ItemID.ROGUE_MASK, "Rogue"),
	LESSER_DEMONS("Lesser demons", ItemID.LESSER_DEMON_MASK),
	LIZARDS("Lizards", ItemID.DESERT_LIZARD, "Desert lizard", "Sulphur lizard", "Small lizard", "Lizard"),
	LIZARDMEN("Lizardmen", ItemID.LIZARDMAN_FANG, "Lizardman", "Lizardman brute", "Lizardman shaman"),
	MINIONS_OF_SCABARAS("Minions of scabaras", ItemID.GOLDEN_SCARAB, "Scarabite", "Scarab swarm", "Locust rider", "Scarab mage"),
	MINOTAURS("Minotaurs", ItemID.ENSOULED_MINOTAUR_HEAD),
	MITHRIL_DRAGONS("Mithril dragons", ItemID.MITHRIL_DRAGON_MASK),
	MOGRES("Mogres", ItemID.MOGRE),
	MOLANISKS("Molanisks", ItemID.MOLANISK),
	MONKEYS("Monkeys", ItemID.ENSOULED_MONKEY_HEAD, "Monkey guard", "Monkey zombie"),
	MOSS_GIANTS("Moss giants", ItemID.HILL_GIANT_CLUB, "Bryophyta"),
	MUTATED_ZYGOMITES("Mutated zygomites", ItemID.MUTATED_ZYGOMITE, 7, ItemID.FUNGICIDE_SPRAY_0, "Zygomite", "Fungi", "Ancient zygomite", "Mutated zygomite"),
	NECHRYAEL("Nechryael", ItemID.NECHRYAEL, "Nechryarch"),
	OGRES("Ogres", ItemID.ENSOULED_OGRE_HEAD, "Skogre", "Zogre", "Ogre chieftain", "Ogre shaman"),
	OTHERWORLDLY_BEING("Otherworldly beings", ItemID.GHOSTLY_HOOD),
	PYREFIENDS("Pyrefiends", ItemID.PYREFIEND, "Flaming pyrelord"),
	RATS("Rats", ItemID.RATS_TAIL, "Giant rat"),
	RED_DRAGONS("Red dragons", ItemID.BABY_RED_DRAGON, "Baby red dragon", "Brutal red dragon"),
	ROCKSLUGS("Rockslugs", ItemID.ROCKSLUG, 4, ItemID.BAG_OF_SALT),
	RUNE_DRAGONS("Rune dragons", ItemID.RUNE_DRAGON_MASK),
	SCORPIA("Scorpia", ItemID.SCORPIAS_OFFSPRING),
	CHAOS_DRUIDS("Chaos druids", ItemID.ELDER_CHAOS_HOOD, "Elder Chaos druid", "Chaos druid"),
	BANDITS("Bandits", ItemID.BANDIT, "Bandit"),
	MAGIC_AXES("Magic axes", ItemID.IRON_BATTLEAXE, "Magic axe"),
	SCORPIONS("Scorpions", ItemID.ENSOULED_SCORPION_HEAD, "King scorpion"),
	SARACHNIS("Sarachnis", ItemID.SRARACHA),
	SEA_SNAKES("Sea snakes", ItemID.SNAKE_CORPSE, "Sea Snake Hatchling", "Sea Snake Young"),
	SHADES("Shades", ItemID.SHADE_ROBE_TOP, "Loar Shadow", "Loar Shade", "Phrin Shadow", "Phrin Shade", "Riyl Shadow", "Riyl Shade", "Asyn Shadow", "Asyn Shade", "Fiyr Shadow", "Fiyr Shade"),
	SHADOW_WARRIORS("Shadow warriors", ItemID.BLACK_FULL_HELM),
	SKELETAL_WYVERNS("Skeletal wyverns", ItemID.SKELETAL_WYVERN),
	SKOTIZO("Skotizo", ItemID.SKOTOS),
	SKELETONS("Skeletons", ItemID.SKELETON_GUARD, "Skeleton mage", "Skogre"),
	SMOKE_DEVILS("Smoke devils", ItemID.SMOKE_DEVIL),
	SPIDERS("Spiders", ItemID.HUGE_SPIDER, "Giant spider", "Blessed spider", "Poison spider", "Deadly red spider", "Jungle spider", "Ice spider", "Kalrag"),
	SPIRITUAL_CREATURES("Spiritual creatures", ItemID.DRAGON_BOOTS, "Spiritual ranger", "Spiritual mage", "Spiritual warrior"),
	STEEL_DRAGONS("Steel dragons", ItemID.STEEL_DRAGON),
	SULPHUR_LIZARDS("Sulphur Lizards", ItemID.SULPHUR_LIZARD),
	SUQAHS("Suqahs", ItemID.SUQAH_TOOTH),
	TERROR_DOGS("Terror dogs", ItemID.TERROR_DOG),
	THERMONUCLEAR_SMOKE_DEVIL("Thermonuclear Smoke Devil", ItemID.PET_SMOKE_DEVIL),
	TROLLS("Trolls", ItemID.TROLL_GUARD, "Mountain troll", "Thrower troll", "Ice troll", "Troll general", "Troll spectator", "Rock (Troll)"),
	TUROTH("Turoth", ItemID.TUROTH),
	TZHAAR("Tzhaar", ItemID.ENSOULED_TZHAAR_HEAD, "TzHaar-Ket", "TzHaar-Xil", "TzHaar-Hur", "TzHaar-Mej", "TzHaar-Ket"),
	VAMPIRES("Vampires", ItemID.STAKE, "Vampyre juvenile", "Feral vampyre", "Vyrewatch"),
	VENENATIS("Venenatis", ItemID.VENENATIS_SPIDERLING),
	VETION("Vet'ion", ItemID.VETION_JR),
	VORKATH("Vorkath", ItemID.VORKI),
	WALL_BEASTS("Wall beasts", ItemID.SWAMP_WALLBEAST),
	WATERFIENDS("Waterfiends", ItemID.WATER_ORB),
	WEREWOLVES("Werewolves", ItemID.WOLFBANE, "Werewolf"),
	WOLVES("Wolves", ItemID.GREY_WOLF_FUR, "Wolf", "White wolf", "Big wolf", "Ice wolf", "Jungle wolf", "Dire wolf", "Desert wolf"),
	WYRMS("Wyrms", ItemID.WYRM),
	ZILYANA("Commander Zilyana", ItemID.PET_ZILYANA),
	ZOMBIES("Zombies", ItemID.ZOMBIE_HEAD, "Undead", "Undead one", "Undead cow", "Zombie pirate"),
	ZULRAH("Zulrah", ItemID.PET_SNAKELING),
	ZUK("TzKal-Zuk", ItemID.TZREKZUK, 101890);
	//</editor-fold>

	private static final Map<String, Task> tasks;
	static final List<String> LOCATIONS = ImmutableList.of(
		"", // no location is a valid location
		"Abyss",
		"Ancient Cavern",
		"Asgarnian Ice Dungeon",
		"Brimhaven Dungeon",
		"Brine Rat Cavern",
		"Catacombs of Kourend",
		"Chasm of Fire",
		"Clan Wars",
		"Death Plateau",
		"Evil Chicken's Lair",
		"Fossil Island",
		"Fremennik Slayer Dungeon",
		"God Wars Dungeon",
		"Iorwerth Dungeon",
		"Kalphite Lair",
		"Karuulm Slayer Dungeon",
		"Keldagrim",
		"Kraken Cove",
		"Lighthouse",
		"Lithkren Vault",
		"Lizardman Canyon",
		"Lizardman Settlement",
		"Molch",
		"Mount Quidamortem",
		"Mourner Tunnels",
		"Ogre Enclave",
		"Slayer Tower",
		"Smoke Devil Dungeon",
		"Smoke Dungeon",
		"Stronghold of Security",
		"Stronghold Slayer Dungeon",
		"task-only Kalphite Cave",
		"Taverley Dungeon",
		"Troll Stronghold",
		"Waterbirth Island",
		"Waterfall Dungeon",
		"Wilderness",
		"Witchaven Dungeon",
		"Zanaris"
	);

	private final String name;
	private final int itemSpriteId;
	private final String[] targetNames;
	private final int weaknessThreshold;
	private final int weaknessItem;
	private final int expectedKillExp;

	static
	{
		ImmutableMap.Builder<String, Task> builder = new ImmutableMap.Builder<>();

		for (Task task : values())
		{
			builder.put(task.getName().toLowerCase(), task);
		}

		tasks = builder.build();
	}

	Task(String name, int itemSpriteId, String... targetNames)
	{
		Preconditions.checkArgument(itemSpriteId >= 0);
		this.name = name;
		this.itemSpriteId = itemSpriteId;
		this.weaknessThreshold = -1;
		this.weaknessItem = -1;
		this.targetNames = targetNames;
		this.expectedKillExp = 0;
	}

	Task(String name, int itemSpriteId, int weaknessThreshold, int weaknessItem, String... targetNames)
	{
		Preconditions.checkArgument(itemSpriteId >= 0);
		this.name = name;
		this.itemSpriteId = itemSpriteId;
		this.weaknessThreshold = weaknessThreshold;
		this.weaknessItem = weaknessItem;
		this.targetNames = targetNames;
		this.expectedKillExp = 0;
	}

	Task(String name, int itemSpriteId, int expectedKillExp)
	{
		Preconditions.checkArgument(itemSpriteId >= 0);
		this.name = name;
		this.itemSpriteId = itemSpriteId;
		this.weaknessThreshold = -1;
		this.weaknessItem = -1;
		this.targetNames = new String[0];
		this.expectedKillExp = expectedKillExp;
	}

	@Nullable
	static Task getTask(String taskName)
	{
		Task task = tasks.get(taskName.toLowerCase());
		if (task != null) return task;
		task = tasks.get(taskName.toLowerCase() + "s");
		if (task != null) return task;
		for (Task v : tasks.values())
		{
			String[] targets = v.getTargetNames();
			if (targets == null || targets.length <= 0) continue;
			List<String> tlist = Arrays.asList(targets);
			tlist.replaceAll(String::toLowerCase);
			if (tlist.contains(taskName.toLowerCase())) task = v;
		}
		return task;
	}

	public static Task searchTask(String term)
	{
		for (Task v : tasks.values())
		{
			if (v.name.toLowerCase().contains(term.toLowerCase()))
			{
				return v;
			}
		}
		return null;
	}
}
