import filecmp
import sys
from pathlib import Path


def main():

    allGood = True

    for filename in Path('./testFiles').rglob('*.ast'):
        correctPath = "correctOut/" + filename.stem + ".ast"
        #print(correctPath + " " + str(filename))

        comparison = filecmp.cmp(str(filename), correctPath)
        if comparison == False:
            print(False)
            sys.exit()


    print("All Tests Passed")

if __name__ == "__main__":
    main()
