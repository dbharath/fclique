package net.pcheck.crowler.model;

import net.pcheck.constants.Constants.SearchType;


/**
 * Created by vivek.gupta
 */
public class Query {
    private String queryString;
    private Integer pageNumber;
    private Integer maxProductToFetch = 0;
    private SearchType searchType = SearchType.ByProductName;

    public Query(String queryString, Integer pageNumber, Integer maxProductToFetch) {
        this.queryString = queryString.trim().replaceAll(" +", "+");
        this.maxProductToFetch = maxProductToFetch;
        try {
            this.pageNumber = pageNumber;            
        } catch (Exception e) {
            this.pageNumber = 1;
            
        }
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass())
            result = false;
        else {
            Query query = (Query) object;
            if (this.queryString.equals(query.queryString) && this.pageNumber.equals(query.pageNumber) && this.maxProductToFetch.equals(maxProductToFetch))
                result = true;
        }
        return result;

    }

    @Override
    public int hashCode() {
        return this.queryString.hashCode() + this.pageNumber.hashCode() + this.maxProductToFetch.hashCode();
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

	public Integer getMaxProductToFetch() {
		return maxProductToFetch;
	}

	public void setMaxProductToFetch(Integer maxProductToFetch) {
		this.maxProductToFetch = maxProductToFetch;
	}

	public SearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}
    
	
	
    
}
