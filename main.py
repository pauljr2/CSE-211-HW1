from FileIO import FileIO


def main(file):
    # input file methods
    inputList = FileIO.input(file) # List that will be used to make computations
    # Prints the list elements // For testing purposes
    for i in inputList:
        print(i)
    # Computations

    # Output file method
    # FileIO.output() // Not implemented yet

file = open("studentGrades.txt")
main(file)

