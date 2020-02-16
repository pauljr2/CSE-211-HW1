# Gets each row from the text file.
# Each row in the file is an element in a list
# The list is returned

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
    def output(students):
        # inputs a lis with the following format
        # [["Sam", 87.2, 'B'], ["Daniel", 62.0, 'D']]
        output = open("studentGradesOutput.txt", 'w')
        for student in students:
            i = 0
            for x in student:
                if i == 0:
                    output.write(" " * (20 - len(student[i])) + student[i])
                else:
                    output.write(" " + str(student[i]))
                if i == 2:
                    output.write("\n")
                i += 1
        output.close()




