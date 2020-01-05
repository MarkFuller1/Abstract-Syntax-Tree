# Mark Fuller
# Dr.Cerny
# Python - AST generator given root directory to search


import ast
import sys
from pathlib import Path

def main():
    if len(sys.argv) != 2:
        print ("Usage: python genTree.py [path/to/dir]")
        sys.exit()

    root = sys.argv[1]


# search root directory recursively for .py files
    for filename in Path(root).rglob('*.py'):
        with open(filename, "r") as source:

            # generate the tree for each file
            tree = ast.parse(source.read())

            #print the filename
            print(filename)

            #get the tree as a string
            astdata = ast.dump(tree)
            
            #write that string to a file
            astout = open(str(filename) + ".ast", "a")
            astout.write(astdata)
            astout.close()
            

if __name__ == "__main__":
    main()
