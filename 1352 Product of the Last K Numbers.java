class ProductOfNumbers {
    ArrayList<Integer> list = new ArrayList<>();
    int prod = 1;

    public ProductOfNumbers() {
        
    }
    
    public void add(int num) {
        if(num == 0) {
…        int ans = list.get(list.size() - 1);
        if(list.size() == k) return ans;
        return ans / list.get(list.size() - 1 - k);        
    }
}
