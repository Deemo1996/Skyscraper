# Skyscraper
Analysis of Algorithms' Class Project

Find the largest suitable location within a bounded area to build a skyscraper. The area is divided into a grid of N by M cells. Each cell (i,j) as an integer height H[i,j], where 1≤i≤N and1≤j≤M.

You have four different tasks:

1. Design and implement an Θ(N · M) time algorithm that uses O(M) space for computing a largest area square block with all cells have the height C. Note that this problem is the same as the Problem 4 of Exam 1 when C = 1 and N = M.

2. Design and implement an Θ(N · M2) time algorithm that uses O(M) space for computing a largest area rectangle block with all cells have the height C.

3. Design and implement an Θ(N · M) time algorithm that uses O(M) space for computing a largest area rectangle block with all cells have the height C.

4. Design and implement an Θ(N·M2·C) time algorithm that uses O(M·C) space for computing a largest area rectangle block with the height difference of at most C for any two cells in the block. In other words, for any two cells (i1, j1) , (i2, j2) in the block, we have |H[i1, j1] − H[i2, j2]| ≤ C.
