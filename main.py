from FileIO import FileIO


def main(file):
    # input file methods
    inputList = FileIO.input(file) # List that will be used to make computations
    # Prints the list elements // For testing purposes
    for i in inputList:
        print(i)
    # Computations

    # Gets each row from the text file.
    # Each row in the file is an element in a list
    # The list is returned
    def getInput(file):
        list = []
        s = file.readline()
        while s != "":
            # split the line we read in and put it into a list
            list.append(s.split())
            s = file.readline()
        file.close()

        for i in list:
            print(i)

        return list

    # 20% quizzes, 30% homework, 50% exams

    def getCourseAvg(student):
        avg = 0
        avg = ((int(student[1]) + int(student[2]) + int(student[3])) / 3) * .20
        avg += ((int(student[4]) + int(student[5]) + int(student[6]) + int(student[7]) + int(student[8])) / 5) * .3
        avg += ((int(student[9]) + int(student[10])) / 2) * .5
        avg = round(avg, 1)
        # print(avg)
        return avg

    def getLetterGrade(number):
        if number >= 90:
            return 'A'
        elif 80 <= number < 90:
            return 'B'
        elif 70 <= number < 80:
            return 'C'
        elif 60 <= number < 70:
            return 'D'
        elif number < 60:
            return 'F'

    def populateList(listB):

        list = []
        # add the name
        list.append(listB[0])

        # getCourseAvg
        list.append(getCourseAvg(listB))

        # getLetterGrade
        list.append(getLetterGrade(getCourseAvg(listB)))

        return list

    listB = getInput(open("studentGrades.txt"))

    newList = []
    k = 0
    for x in listB:
        newList.append(populateList(listB[k]))
        k += 1

    for x in newList:
        print(x)

    # Output file method
    FileIO.output(newList) # Not implemented yet - enter list name as arg

file = open("studentGrades.txt")
main(file)

