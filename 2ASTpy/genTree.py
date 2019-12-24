import ast
from pathlib import Path

def main():
    for filename in Path('.').rglob('*.py'):
        with open(filename, "r") as source:
            tree = ast.parse(source.read())
            print(filename)
            print(ast.dump(tree))

if __name__ == "__main__":
    main()
