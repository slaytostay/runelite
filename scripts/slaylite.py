import os
import glob
import subprocess
from shutil import copyfile
import ntpath

home_folder = os.environ['USERPROFILE']
dest_folder = file_dest = home_folder + '\\IdeaProjects\\slaylite\\';
jar_folder = dest_folder + 'runelite-client\\target\\'

old_jars = glob.glob(dest_folder + '*-shaded.jar')
for f in old_jars:
    os.remove(f)

list_of_files = glob.glob(jar_folder + '*-shaded.jar')
latest_file = max(list_of_files, key=os.path.getctime)
file_dest = dest_folder + ntpath.basename(latest_file)
copyfile(latest_file, dest_folder + 'RuneLite.jar')

subprocess.Popen(dest_folder + "start.bat", cwd=dest_folder)