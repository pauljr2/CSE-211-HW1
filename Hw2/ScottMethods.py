class ScottMethods:
    # This method takes in a string. For example, "Scott Cogan, Jack Paul, Abby Danger, Drew Grimes"
    # This method returns a string. For example, "Cogan, Scott, Jack Paul, Abby Danger, Drew Grimes"
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
        authors = ScottMethods.formatNames(str.replace(lines[2], "Author: ", "", 1))
        # Gets the title without the "Title: " part
        title = str.replace(lines[3], "Title: ", "", 1)
        # Creates the first part of every type of media citation
        result[1] = result[1] + authors + ", " + title

        # Journal Case:
        if lines[0] == "Journal":
            # result[1] is the string or the value of the dictionary
            result[1] = result[1] + ScottMethods.JournalHelper(lines)
        #Book Case:
        elif lines[0] == "Book":
            result[1] = result[1] + ScottMethods.BookHelper(lines)
        #Conference Case:
        elif lines[0] == "Conference":
            result[1] = result[1] + ScottMethods.ConferenceHelper(lines)

        return result

    # Returns the second part of a journal string as dictionary value
    def JournalHelper(list):
        journal = str.replace(list[4], "Journal: ", "", 1)
        publisher = str.replace(list[5], "Publisher: ", "", 1)
        volume = str.replace(list[7], "Volume: ", "", 1)
        number = str.replace(list[8], "Number: ", "", 1)
        date = str.replace(list[6], "Date: ", "", 1)
        # Places them in order and formats the journal info
        jstring = ", " + journal + ", " + publisher + ":" + volume + "(" + number + "), " + date + "."
        return jstring

    #Returns the second part of a book string as dictionary value
    def BookHelper(list):
        publisher = str.replace(list[4], "Publisher: ", "", 1)
        date = str.replace(list[5], "Date: ", "", 1)
        # Places them in order and formats the journal info
        bstring = ", " + publisher + ", " + date + "."
        return bstring

    def ConferenceHelper(list):
        conference = str.replace(list[4], "Conference: ", "", 1)
        date = str.replace(list[5], "Date: ", "", 1)
        location = str.replace(list[6], "Location: ", "", 1)
        pages = str.replace(list[7], "Pages: ", "", 1)
        # Places them in order and formats the journal info
        cstring = ", " + conference + ", " + date + ", " + location + ", " + pages + "."
        return cstring
