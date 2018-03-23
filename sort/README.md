# Sorting Java Implementation

| Algorithm     | Time           | Stable  |
| ------------- |:-------------:| -----:|
| Bubble      | O(n^2) |stable |
| Insert      |  O(n^2)      |   stable |
|  Select|  Best case:O(n^2)      |    stable |
|  Shell|  O(n^1.5)      |    stable |
|  Quick|  O(nlogn)     |    unstable |
|  Merge|  O(nlogn)     |    unstable |
|  Radix|  O(n)     |    stable |


## Bubble
```java
static void bubblesort (Comparable[] array) { 
    for (int i=0; i<array.length-1; i++) {// Bubble up i¡¯th record
        for (int j=array.length-1; j>i; j--){
            if (array[j].compareTo(array[j-1])<0){
                swap(array, j, j-1);
            }
        }
    }
}
```
### Output:

```
20  42  17  13  28  14  23  15  j=1
i=1
20  17  42  13  28  14  23  15  j=2
17  20  42  13  28  14  23  15  j=1
i=2
17  20  13  42  28  14  23  15  j=3
17  13  20  42  28  14  23  15  j=2
13  17  20  42  28  14  23  15  j=1
i=3
13  17  20  28  42  14  23  15  j=4
i=4
13  17  20  28  14  42  23  15  j=5
13  17  20  14  28  42  23  15  j=4
13  17  14  20  28  42  23  15  j=3
13  14  17  20  28  42  23  15  j=2
i=5
13  14  17  20  28  23  42  15  j=6
13  14  17  20  23  28  42  15  j=5
i=6
13  14  17  20  23  28  15  42  j=7
13  14  17  20  23  15  28  42  j=6
13  14  17  20  15  23  28  42  j=5
13  14  17  15  20  23  28  42  j=4
13  14  15  17  20  23  28  42  j=3
i=7
```

## Insert
```java
static void insertsort (Comparable[] array) { 
    for (int i=1; i<array.length; i++){ // insert ith Comparable
        for (int j=i; (j>0)&&array[j].compareTo(array[j-1])<0; j--){//if j<=0||array[j-1]<array[j],then quit,otherwise swap,swap.....
            swap(array, j, j-1); 
        }
    }
}
```
## Select 
```java
static void selectsort (Comparable[] array){
    for (int i=0; i<array.length-1; i++) {
        int lowindex = i;
        for (int j=i+1; j<array.length; j++)
            if (array[j].compareTo(array[lowindex])<0)
                lowindex = j;      
        swap(array, i, lowindex);
    }
}
```
### Output:
```
13  20  17  42  28  14  23  15  lowindex=3i=0
13  14  17  42  28  20  23  15  lowindex=5i=1
13  14  15  42  28  20  23  17  lowindex=7i=2
13  14  15  17  28  20  23  42  lowindex=7i=3
13  14  15  17  20  28  23  42  lowindex=5i=4
13  14  15  17  20  23  28  42  lowindex=6i=5
13  14  15  17  20  23  28  42  lowindex=6i=6
```
## Shell
```java
static void shellsort (Comparable[] array) {
    for (int gap=array.length/2; gap>0; gap/=2){
        for (int i = gap; i< array.length; i++){
            for (int j=i; j>=gap && array[j].compareTo(array[j-gap])<0; j-=gap){
                swap(array, j, j-gap);
            }
        }
    }
}
```
### Output:
```

28  20  17  13  42  14  23  15  j=4
i=4
28  14  17  13  42  20  23  15  j=5
i=5
i=6
i=7
gap=4
17  14  28  13  42  20  23  15  j=2
i=2
17  13  28  14  42  20  23  15  j=3
i=3
i=4
i=5
17  13  28  14  23  20  42  15  j=6
17  13  23  14  28  20  42  15  j=4
i=6
17  13  23  14  28  15  42  20  j=7
i=7
gap=2
13  17  23  14  28  15  42  20  j=1
i=1
i=2
13  17  14  23  28  15  42  20  j=3
13  14  17  23  28  15  42  20  j=2
i=3
i=4
13  14  17  23  15  28  42  20  j=5
13  14  17  15  23  28  42  20  j=4
13  14  15  17  23  28  42  20  j=3
i=5
i=6
13  14  15  17  23  28  20  42  j=7
13  14  15  17  23  20  28  42  j=6
13  14  15  17  20  23  28  42  j=5
i=7
gap=1
```
## Quick
```java
static void quicksort (Comparable[] array, int i, int j) {
    int pivotindex = findpivot (array, i, j);
    swap (array, pivotindex, j);//change pivot to right side
    int k = partition (array, i-1, j, array[j].key);
    swap (array, k, j);
    if ((k-i)>1) quicksort (array, i, k-1);
    //k (i.e. pivot already right
    if ((j-k)>1) quicksort (array, k+1, j);
}
static int findpivot (Comparable[] array, int i, int j) {
    return j;
}
static int partition (Comparable[] array, int l, int r, int pivot) {
    do {
        while (array[++l].compareTo(pivot) < 0);//find first left > pivot
        while ((r!=0) && (array[--r].compareTo(pivot) > 0));//find first right < pivot
        swap(array, l, r);
    } while (l<r);
    swap(array, l, r);
    return l;
}
```
### Output:
```
 qsort0,7
partition-1,7,,15
14  17  13  20  28  42  23  15  
14  13  17  20  28  42  23  15  end partition
k=2  pivotindex=7
14  13  15  20  28  42  23  17  qsort0,1
partition-1,1,,13
14  13  15  20  28  42  23  17  
14  13  15  20  28  42  23  17  end partition
k=0  pivotindex=1
13  14  15  20  28  42  23  17  qsort3,7
partition2,7,,17
13  14  20  15  28  42  23  17  
13  14  15  20  28  42  23  17  end partition
k=3  pivotindex=7
13  14  15  17  28  42  23  20  qsort4,7
partition3,7,,20
13  14  15  28  17  42  23  20  
13  14  15  17  28  42  23  20  end partition
k=4  pivotindex=7
13  14  15  17  20  42  23  28  qsort5,7
partition4,7,,28
13  14  15  17  20  42  23  28  
13  14  15  17  20  23  42  28  end partition
k=6  pivotindex=7
13  14  15  17  20  23  28  42  
```
### Non-recurse
```java
static void qsort (Comparable[] array, int oi, int oj){
    int THRESHOLD = 5;
    int[] Stack = new int[10000000];
    int listsize = oj-oi+1;
    int top = -1;
    int pivot;
    int pivotindex, l, r;
    Stack[++top]=oi;
    Stack[++top]=oj;
    while (top > 0){
        int j = Stack[top--];
        int i = Stack[top--];
        pivotindex = (i+j)/2;
        pivot = array[pivotindex].key;
        swap(array, pivotindex, j);
        l = i-1;
        r = j;
        do {
            while (array[++l].compareTo(pivot) < 0);
            while ((r!=0) && (array[--r].compareTo(pivot) > 0));
            swap(array, l, r);
        } while (l<r);
        swap(array, l, r);
        swap(array, l, j);
        if ((l-i)>THRESHOLD) {//THRESHOLD:when n < this qsort is slow-->use insertsort
            Stack[++top] = i;
            Stack[++top] = l-1;
        }
        if ((j-l)>THRESHOLD) {
            Stack[++top] = l+1;
            Stack[++top] = j;
        }
    }
    insertsort(array);
}
```
## Merge
```java
static void mergesort (Comparable[] array, Comparable[] temp, int l, int r) {
    if  (l == r) return ;
    int mid = (l+r)/2;
    mergesort (array, temp, l, mid);
    mergesort (array, temp, mid+1, r);
    //copy to temp
    for (int i=l; i<=r; i++)
        temp[i] = array[i];
    //Do the merge operation back to array
    int i1=l; int i2=mid+1;
    for (int curr=l; curr<=r; curr++) {
        if (i1 == mid+1)//left half finished :copy right half to rest of array
            array[curr] = temp[i2++];
        else if (i2>r)//left half finished :copy right half to rest of array
            array[curr] = temp[i1++];
            //who smaller who assigned to array
        else if (temp[i1].key <= temp[i2].key)
            array[curr] = temp[i1++];
        else
            array[curr] = temp[i2++];
    }
}
```
### Non-recurse
```java
static void mergesortNonRecur (Comparable[] array, Comparable[] temp, int l, int r) {
    int THRESHOLD = 5;
    int i, j, k, mid = (l+r)/2;
    if (l ==r ) return;
    if ((mid-l) >= THRESHOLD) mergesort (array, temp, l, mid);
    //else insertsort (array, l, l, mid-l+1);
    if ((r-mid) > THRESHOLD) mergesort (array, temp, mid+1, r);
    // else insertsort (array, mid+1, r-mid);
    for (i=l; i<=mid; i++) temp[i] = array[i];
    for (j=1;j<=r-mid; j++) temp[r-j+1] = array[j+mid];
    int a = temp[l].key; 
    int b = temp[r].key;
    for (i=l, j=r, k=l; k<=r; k++)
        if (a<b) { 
            array[k] = temp[i++]; 
            a = temp[i].key; 
        }
        else { 
            array[k] = temp[j--]; 
            b = temp[j].key; 
        }
    }
}   
```
## Radix
```java
static void radix (Comparable[] A, Comparable[] B, int k, int r, int[] count) {
    int i, j, rtok;
    for (i=0, rtok=1; i<k; i++, rtok*=r) {
        for (j=0; j<r; j++) count[j] = 0;
        for (j=0; j<A.length; j++) count[(A[j].key/rtok)%r]++;
        for (j=1; j<r; j++) count[j] = count[j-1] + count[j];
        for (j=A.length-1; j>=0; j--)B[--count[(A[j].key/rtok)%r]] = A[j];
        for (j=0; j<A.length; j++) A[j] = B[j];
    }
}
```

	


