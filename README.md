# RegionCounter2D

This console program counts areas and marks that hardcoded given region.

Only area count is requested, marking areas is additionally provided for this solution.

Solution Algorithm:
1. Does the pixel to the left (West) have the same value as the current pixel?
- Yes – We are in the same region. Assign the same label to the current pixel
- No – Check next condition
2. Do both pixels to the North and West of the current pixel have the same value as the current pixel but not the same label?
- Yes – We know that the North and West pixels belong to the same region and must be merged. Assign the current pixel the minimum of the North and West labels, and record their equivalence relationship
- No – Check next condition
3. Does the pixel to the left (West) have a different value and the one to the North the same value as the current pixel?
- Yes – Assign the label of the North pixel to the current pixel
- No – Check next condition
4. Do the pixel's North and West neighbors have different pixel values than current pixel?
- Yes – Create a new label id and assign it to the current pixel

---------------------------------------------------------------------

1. Get the neighboring elements of the current element
2. If there are no neighbors, uniquely label the current element and continue
3. Otherwise, find the neighbor with the smallest label and assign it to the current element
4. Store the equivalence between neighboring labels
