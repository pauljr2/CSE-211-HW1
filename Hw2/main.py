from LibraryDict import  LibraryDict
from ScottMethods import ScottMethods

def __main__():
    libraryDict = LibraryDict()
    s = ScottMethods.formatNames("Scott Cogan, Jack Paul, Abby Danger, Drew Grimes")
    print(s)
    s2 = "Journal\nKey: Kiper1996\nAuthor: James Kiper\nTitle: Criteria for Evaluation of Visual Programming Languages\nJournal: Journal of Visual Languages & Computing\nPublisher: Elsevier\nDate: 1996\nVolume: 8\nNumber: 2\n"
    result = ScottMethods.getKeyValue(s2)
    print(result[1])

__main__()