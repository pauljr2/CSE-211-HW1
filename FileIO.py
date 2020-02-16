# Gets each row from the text file.
# Each row in the file is an element in a list
# The list is returned

import sys
from typing import List, Any


class FileIO:
    # Reads a file
    def input(file):
        l = []
        s = file.readline()
        while s != "":
            l.append(s)
            s = file.readline()
        file.close()
        return l
    def output(dict):
        output = open("studentGradesOutput.txt", 'w')




