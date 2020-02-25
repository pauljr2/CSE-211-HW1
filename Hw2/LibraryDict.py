# Scotts Methods

def formatNames(message):
    nameList = message.split(", ")
    endString = ""
    i = 0
    for name in nameList:
        if i == 0:
            shortNameList = name.split()
            endString = shortNameList[1] + ", " + shortNameList[0]
        else:
            endString = endString + ", " + name
        i = i + 1
    return endString

    # This method takes in an entry

"""
This formats a muti-line string. For example,

Journal
Key: Kiper1996
Author: James Kiper
Title: Criteria for Evaluation of Visual Programming Languages
Journal: Journal of Visual Languages & Computing
Publisher: Elsevier
Date: 1996
Volume: 8
Number: 2

"""

def getKeyValue(string):
    # Puts each line in list
    lines = string.split("\n")
    # setup to return a key and value
    result = [str.replace(lines[1], "Key: ", "", 1), ""]
    # Is it a book, journal etc.
    type = lines[0]
    # Gets the names in the formated way
    authors = formatNames(str.replace(lines[2], "Author: ", "", 1))
    # Gets the title without the "Title: " part
    title = str.replace(lines[3], "Title: ", "", 1)
    # Creates the first part of every type of media citation
    result[1] = result[1] + authors + ", " + title

    # Journal Case:
    if lines[0] == "Journal":
        # result[1] is the string or the value of the dictionary
        result[1] = result[1] + JournalHelper(lines)

    # Book Case:
    elif lines[0] == "Book":
        result[1] = result[1] + BookHelper(lines)

    # Conference Case:
    elif lines[0] == "Conference":
        result[1] = result[1] + ConferenceHelper(lines)

    return result


# Returns the second part of the string in the dictionary value
def JournalHelper(list):
    string = ""
    # Gets the values without the leading "...: "
    journal = str.replace(list[4], "Journal: ", "", 1)
    publisher = str.replace(list[5], "Publisher: ", "", 1)
    volume = str.replace(list[7], "Volume: ", "", 1)
    number = str.replace(list[8], "Number: ", "", 1)
    date = str.replace(list[6], "Date: ", "", 1)
    # Places them in order and formats the journal info
    string = ", " + journal + ", " + publisher + ":" + volume + "(" + number + "), " + date
    return string

#Returns the second part of a book string as dictionary value
def BookHelper(list):
    publisher = str.replace(list[4], "Publisher: ", "", 1)
    date = str.replace(list[5], "Date: ", "", 1)
    # Places them in order and formats the journal info
    bstring = ", " + publisher + ", " + date + "."
    return bstring

def ConferenceHelper(list):
    conference = str.replace(list[4], "Conference: ", "", 1)
    date = str.replace(list[6], "Date: ", "", 1)
    location = str.replace(list[5], "Location: ", "", 1)
    pages = str.replace(list[7], "Pages: ", "", 1)
    # Places them in order and formats the journal info
    cstring = ", in " + conference + ", " + date + ", " + location + ", " + pages + "."
    return cstring


dict = {}


book_str = ""
journal_str = ""
conference_str = ""


with open("Step3Data.txt") as fp:
    for line in fp:

        if line == "Book\n":
            book_str += line
            for i in range(0,5):
                book_str += next(fp)
            dict[getKeyValue(book_str)[0]] = getKeyValue(book_str)[1]
            book_str = ""

        if line == "Journal\n":
            journal_str += line
            for i in range(0,8):
                journal_str += next(fp)
            dict[getKeyValue(journal_str)[0]] = getKeyValue(journal_str)[1]
            journal_str = ""

        if line == "Conference\n":
            conference_str += line
            for i in range(0,7):
                conference_str += next(fp)
            dict[getKeyValue(conference_str)[0]] = getKeyValue(conference_str)[1]
            conference_str = ""


print("Please enter key: ")
input_key = input()

if input_key in dict:
    print(input_key + "        " + dict.get(input_key, "none"))
else:
    print("Sorry, this key isn't in the dictionary. Try another one.")



