package com.vijfmusketiers.ProjectB.breadcrumbs;

public class BreadcrumbsDto {
    private Long id;

    private long xCo;

    private long yCo;

    public void setId(Long id) {
        this.id = id;
    }
    public void setxCo(long xCo) {
        this.xCo = xCo;
    }
    public void setyCo(long yCo) {
        this.yCo = yCo;
    }

    public Long getId() {
        return id;
    }
    public long getxCo() {
        return xCo;
    }
    public long getyCo() {
        return yCo;
    }
}
