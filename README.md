# Travelling-Salesman-Problem
Travelling Salesman Problem (T.S.P) customized to work with the Smart Bin Dashboard application.

# Description 
The T.S.P algorithm is used to find the optimal path from the source point to all other points in a graph and back to the source(the final destination is the source itself) traversing each point only once.<br>
Examining the algorithm in more detail, the source and destination points are the same. For the smart bin dashboard application this algorithm has to be modified. The modified algorithm has a source point(the current location of the garbage truck) which is dynamic and a destination point(the garbage truck base) that is static. The source point changes as the location of the garbage truck changes. The destination point however, remains the same during the application lifecycle.<br>
Overall the new algorithm finds the optimal path a garbage truck has to traverse to collect the garbage and return to it's base.
