package entity;

public class Book {

	private int id;
	private String isbn;
	private String name;
	private String author;
	private String publish;
	private int count;
	private int currcount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCurrcount() {
		return currcount;
	}
	public void setCurrcount(int currcount) {
		this.currcount = currcount;
	}
	@Override
	public String toString() {
		return  isbn + "\t\t" + name + "\t\t" + author + "\t\t" + publish
				+ "\t\t" + count + "\t\t" + currcount ;
	}

	
	
}
