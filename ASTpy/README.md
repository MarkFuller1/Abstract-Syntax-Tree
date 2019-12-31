# Python AST Generator

## Execution

To run the program simply run the following command

`pip install --user --requirement requirements.txt` 

then 

`python genTree.py [path to dir]`

pip install only needs to be run once.

## Functionalilty

Given a direactory acting as "root" this program will generate an abstract syntax tree for each `.py` file it finds withing the root. Said AST's will be stored in files named `<filename>.ast`

## Unit Tests

`./unitTest.sh`
