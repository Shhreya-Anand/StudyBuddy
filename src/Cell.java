public abstract class Cell{
    protected int row;
    protected int col;
    protected CellType type;

    public Cell(){
        this.row=0;
        this.col=0;
        this.type=CellType.COMMON;
    }

    public Cell(int row,int col,CellType type){
        this.row=row;
        this.col=col;
        this.type=type;
    }

    public abstract void playerEntryTrigger(Player player);

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public CellType getType(){
        return this.type;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setCol(int col){
        this.col = col;
    }

    public void setType(CellType type){
        this.type = type;
    }

}