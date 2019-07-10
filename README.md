# vatchecker
Json parsed using Gson library [1].
I provide two solutions for the problem, see VatChecker.solutionA & VatChecker.solutionB. 

SolutionA is the cleanest solution and I would prefer it in case we need to add additional functionality to the code later, like finding the top 5 countries instead of top 3. 


SolutionB is the efficient one because it doesn't sort the array (O(n) complexity). It would be preferable to solve the same problem for large amount of data.

Test cases for SolutionB were implemented.

[1] https://github.com/google/gson
