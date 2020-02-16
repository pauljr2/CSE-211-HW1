# Gets each row from the text file.
# Each row in the file is an element in a list
# The list is returned
def getInput(file):
    l = []
    s = file.readline()
    while s != "":
        l.append(s)
        s = file.readline()
    file.close()
    for i in l:
        print(i)
getInput(open("studentGrades.txt"))