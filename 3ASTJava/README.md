# AST Java Parser

This project is compiled and installed using the maven build cycle

# Execution

`mvn clean install package`

## Functionalilty

Given a direactory acting as "root" this program will generate an abstract syntax
tree for each `.java` file it finds within the root. Said AST's will be stored 
in files named `<filename>.ast`

