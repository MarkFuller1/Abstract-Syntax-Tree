import filecmp
import sys
from pathlib import Path


def main():

    allGood = True

    for filename in Path('./testFiles').rglob('*.ast'):
        correctPath = "correctOut/" + filename.stem + ".ast"
        #print(correctPath + " " + str(filename))

        comparison = filecmp.cmp(str(filename), correctPath)
        print(str(filename) + " " + str(comparison))

        if comparison == False:
            allGood = False
            print("\t^^ Files do not match");
            sys.exit()

    if allGood == True:
        print("All Tests Passed")
    else:
        print("One of the tests failed")

if __name__ == "__main__":
    main()
