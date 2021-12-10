package com.map524.anothermovieapp;

public class Paginator {
    public int lastPage;
    public int itemsPerPage = 20;
    public int totalNumberOfItems;
    static public int currentPage=1;


    public int getLastPage() {
        return lastPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }


    public int getTotalNumberOfItems() {
        return totalNumberOfItems;
    }

    public void setTotalNumberOfItems(int totalNumberOfItems) {
        this.totalNumberOfItems = totalNumberOfItems;
        this.lastPage = (totalNumberOfItems % this.itemsPerPage == 0) ? totalNumberOfItems / this.itemsPerPage : (totalNumberOfItems / this.itemsPerPage) + totalNumberOfItems % this.itemsPerPage;
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
