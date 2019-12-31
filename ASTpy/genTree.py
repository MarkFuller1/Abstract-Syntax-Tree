# Mark Fuller
# Dr.Cerny
# Python - AST generator given root directory to search


import ast
import sys
from pathlib import Path

# search root directory recursively for .py files
def main():
    if len(sys.argv) != 2:
        print ("Usage: python genTree.py [path/to/dir]")
        sys.exit()

    root = sys.argv[1]


    for filename in Path(root).rglob('*.py'):
        with open(filename, "r") as source:
            tree = ast.parse(source.read())
            print(filename)
            astdata = ast.dump(tree)

            astout = open(str(filename) + ".ast", "a")
            astout.write(astdata)
            astout.close()
            

if __name__ == "__main__":
    main()
