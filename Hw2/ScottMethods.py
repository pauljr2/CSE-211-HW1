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
