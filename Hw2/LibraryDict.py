# CSE 211 | Homework 2 | Team 11

# Format author names
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

# Prepare values for formatting, and establish the key for the specified Journal, Book, or Conference
def getKeyValue(string):
    # Inputs values all together
    lines = string.split("\n")
    # Initializes the key
    result = [str.replace(lines[1], "Key: ", "", 1), ""]
    # Determines type of reference (Book, Journal, Conference)
    type = lines[0]
    # Grabs formatted author names
    authors = formatNames(str.replace(lines[2], "Author: ", "", 1))
    # Gets the title without the "Title: " part
    title = str.replace(lines[3], "Title: ", "", 1)
    # Creates the first part of every type of media citation
    result[1] = result[1] + authors + ", " + title

    # Add formatted Journal data
    if lines[0] == "Journal":
        # result[1] is the string or the value of the dictionary
        result[1] = result[1] + JournalHelper(lines)

    # Add formatted Book data
    elif lines[0] == "Book":
        result[1] = result[1] + BookHelper(lines)

    # Add formatted Conference data
    elif lines[0] == "Conference":
        result[1] = result[1] + ConferenceHelper(lines)

    return result

# Format Journal data
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

# Format Book data
def BookHelper(list):
    publisher = str.replace(list[4], "Publisher: ", "", 1)
    date = str.replace(list[5], "Date: ", "", 1)
    # Places them in order and formats the journal info
    bstring = ", " + publisher + ", " + date + "."
    return bstring

# Format Conference data
def ConferenceHelper(list):
    conference = str.replace(list[4], "Conference: ", "", 1)
    date = str.replace(list[6], "Date: ", "", 1)
    location = str.replace(list[5], "Location: ", "", 1)
    pages = str.replace(list[7], "Pages: ", "", 1)
    # Places them in order and formats the journal info
    cstring = ", in " + conference + ", " + date + ", " + location + ", " + pages + "."
    return cstring

# This is the actual dictionary object which holds all of the library data.
dict = {}

book_str = ""
journal_str = ""
conference_str = ""

#Open the file and add in dictionary values.
with open("Step3Data.txt") as fp:
    for line in fp:
        
        # Add a Book to the dictionary
        if line == "Book\n":
            book_str += line
            for i in range(0,5):
                book_str += next(fp)
            dict[getKeyValue(book_str)[0]] = getKeyValue(book_str)[1]
            book_str = ""
        
        # Add a Journal to the dictionary
        if line == "Journal\n":
            journal_str += line
            for i in range(0,8):
                journal_str += next(fp)
            dict[getKeyValue(journal_str)[0]] = getKeyValue(journal_str)[1]
            journal_str = ""
            
        # Add a Conference to the dictionary
        if line == "Conference\n":
            conference_str += line
            for i in range(0,7):
                conference_str += next(fp)
            dict[getKeyValue(conference_str)[0]] = getKeyValue(conference_str)[1]
            conference_str = ""

# Prompt user for a key, and return data if that key exists
print("Please enter key: ")
input_key = input()

if input_key in dict:
    print(input_key + "        " + dict.get(input_key, "none"))
else:
    print("Sorry, this key isn't in the dictionary. Try another one.")



