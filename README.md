# java-Backend-Maven
Implement a standalone product search program in Java that lists matching products for a user who is
looking for T-shirts.

You are given 3 CSV files, each containing the T-shirts data for Nike, Puma and Adidas respectively.
Sample CSV files links are attached here for reference. You can add more data in existing files or can
add more CSV files for another companies.

Input
Program should accept 4 input parameters
a. Color
b. Size
c. Gender
d. Output Preference 

“Gender” is a String which has two possible values like ‘M’ and ‘F’. M=Male and F=Female.
“Output Preference” is a String which suggests whether the results should be sorted only by Price or by Rating or 
by both Price and Rating.

Expected Behavior and Output

• After getting the inputs, program should search for the T-shirts in all 3 csv files and list the results on
standard output, sorted by Output Preference.
• Program should be written considering that there could be more csv files and at runtime program should
load the files. Please make use of Thread which will look for any new file at a particular configurable
location after some configurable time and load the provided file
• If the suitable T-shirt is not present in any csv files, then program should return a user-friendly error
• Assignment should be implemented as a Maven project.
• Maven 3.x should be used.
• Use available 3
rd party dependencies to read and parse CSV files.
• Write an ant script to build a jar file for the same java program
• Use the new features of Java 8 wherever possible
Output
• Program should display the list of available T-shirts with details on console for provided user inputs.

Evaluation Criteria
• Code Completeness and Correctness
• Use of Java Collections, Threads and Annotations
• Usage of OO Principles, package/class structure, class/function/variable names
• Code in running condition
• Face to face discussion
• Usage of Java 8 new features
