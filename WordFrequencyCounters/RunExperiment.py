import subprocess, time, datetime, csv
from subprocess import STDOUT,PIPE
from datetime import datetime

#import plotly.graph_objects as go
#import pandas as pd

def runJava(javaCmd, numRuns, printWords, fileName, newFile):
    """Runs a given word frequency Java program a given amount of times and prints
    the runtime of each run.

    Args:
        javaCmd (String): Command executed to run the Java program
        numRuns (Integer): Number of experiments to run
        printWords (Boolean): Whether the word frequencies should be printed
        newFile (Boolean): Whether to write to a new file or append to an old one
    """
    if newFile:
        with open(fileName, "w") as csvFile:
            writer = csv.writer(csvFile)
            writer.writerow(["Run #", "Runtime", "Command"])
    else:
        with open(fileName, "a") as csvFile:
            writer = csv.writer(csvFile)

    for i in range(1, numRuns + 1):
        start = datetime.now()
        result = subprocess.run([javaCmd], shell = True, stdout = subprocess.PIPE, universal_newlines=True)
        
        if printWords:
            print(result.stdout)
            print()

        time = datetime.now()-start

        with open(fileName, "a") as csvFile:  
            writer = csv.writer(csvFile)
            writer.writerow([i, time, javaCmd])
        
        print("Run", i, ":", time)
        print()

    csvFile.close()

if __name__ == "__main__":
    """ runJava("java -classpath BoundedBuffer BoundedBufferTable TextFiles/text1.txt TextFiles/text1.txt", 3, True, "experiments.csv", True)
    runJava("java -classpath BoundedBuffer BoundedBufferTable TextFiles/book.txt TextFiles/from7to70.txt TextFiles/book.txt TextFiles/from7to70.txt", 3, False, "experiments.csv", False)
    runJava("java -classpath BoundedBuffer BoundedBufferTable TextFiles/book.txt TextFiles/from7to70.txt TextFiles/book.txt TextFiles/from7to70.txt  TextFiles/book.txt TextFiles/from7to70.txt", 3, False, "experiments.csv", False) """
    runJava("java SequentialFreq TextFiles/testRun.txt TextFiles/testRun.txt", 3, False, "experiments.csv", True)
    runJava("java BasicThreadedFreq TextFiles/testRun.txt TextFiles/testRun.txt", 3, False, "experiments.csv", False)
    runJava("java ByFirstLetterFreq TextFiles/testRun.txt TextFiles/testRun.txt", 3, False, "experiments.csv", False)
    runJava("java MergeFreq TextFiles/testRun.txt TextFiles/testRun.txt", 3, False, "experiments.csv", False)
    runJava("java -classpath BoundedBuffer BoundedBufferTable TextFiles/testRun.txt TextFiles/testRun.txt", 3, False, "experiments.csv", False)
    
    runJava("java SequentialFreq TextFiles/testRun.txt TextFiles/testRun.txt TextFiles/testRun.txt TextFiles/testRun.txt", 3, False, "experiments.csv", False)
    runJava("java BasicThreadedFreq TextFiles/testRun.txt TextFiles/testRun.txt TextFiles/testRun.txt TextFiles/testRun.txt", 3, False, "experiments.csv", False)
    runJava("java ByFirstLetterFreq TextFiles/testRun.txt TextFiles/testRun.txt TextFiles/testRun.txt TextFiles/testRun.txt", 3, False, "experiments.csv", False)
    runJava("java MergeFreq TextFiles/testRun.txt TextFiles/testRun.txt TextFiles/testRun.txt TextFiles/testRun.txt", 3, False, "experiments.csv", False)
    runJava("java -classpath BoundedBuffer BoundedBufferTable TextFiles/testRun.txt TextFiles/testRun.txt TextFiles/testRun.txt TextFiles/testRun.txt", 3, False, "experiments.csv", False)