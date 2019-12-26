import ast
from pathlib import Path

def main():
    for filename in Path('.').rglob('*.py'):
        with open(filename, "r") as source:
            tree = ast.parse(source.read())
            print(filename)
            astdata = ast.dump(tree)

            astout = open(str(filename) + ".ast", "a")
            astout.write(astdata)
            astout.close()
            

if __name__ == "__main__":
    main()
