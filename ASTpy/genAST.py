import ast as tree
import os

def file_get_contents(filename):
    with open(filename, encoding = "ISO-8859-1") as f:
        return f.read()

list = []

for root, dirs, files in os.walk("."):
    for file in files:
        if file.endswith(".py"):
            filename = os.path.join(root, file)
            list.append(filename);

#print(*list, sep = "\n");

file_content = ""

for file in list:
    tree.parse(file)
    #newStuff = file_get_contents(file)
    #file_content  = file_content + newStuff;

#print(file_content);

tree.parse(file_content);





