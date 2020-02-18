from FileIO import FileIO


def main(file):
    # input file methods
    inputList = FileIO.input(file) # List that will be used to make computations

    # Calculates and returns the overall course average grade based on this weighting scale
    # Quizzes - 20% | Homework - 30% | Exams - 50%
    def getCourseAvg(student):
        avg = 0
        avg = ((int(student[1]) + int(student[2]) + int(student[3])) / 3) * .20
        avg += ((int(student[4]) + int(student[5]) + int(student[6]) + int(student[7]) + int(student[8])) / 5) * .3
        avg += ((int(student[9]) + int(student[10])) / 2) * .5
        avg = round(avg, 1)
        # print(avg)
        return avg

    # Returns a letter grade based on the previously calculated overall course average
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

    # Returns a new list populated with student name, overall course average grade, and letter grade
    def populateList(listB):

        list = []
        # add the student name
        list.append(listB[0])

        # getCourseAvg
        list.append(getCourseAvg(listB))

        # getLetterGrade
        list.append(getLetterGrade(getCourseAvg(listB)))

        return list

    listB = FileIO.input(open("studentGrades.txt"))
    
    newList = []
    k = 0
    for x in listB:
        newList.append(populateList(listB[k]))
        k += 1

    # Output file with updated course averages
    FileIO.output(newList)

file = open("studentGrades.txt")
main(file)

