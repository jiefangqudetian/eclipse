package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kaishengit.entity.Book;
import com.kaishengit.entity.BookCard;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.RowMapper;

public class BookCardDao {

	public int countBorrowNum(int cid) {
		String sql = "select count(*) from t_book_card where cid = ?";
		return DbHelp.queryForObject(sql, new CountRowMapper(), cid);
	}

	private class CountRowMapper implements RowMapper<Integer> {

		@Override
		public Integer mapperRow(ResultSet rs) throws SQLException {
			int count = rs.getInt(1);
			return count;
		}
		
	}

	public BookCard findByBidAndCid(int bid, int cid) {
		String sql = "select bid,cid from t_book_card where bid = ? and cid = ?";
		return DbHelp.queryForObject(sql, new RowMapper<BookCard>() {

			@Override
			public BookCard mapperRow(ResultSet rs) throws SQLException {
				BookCard bookCard = new BookCard();
				bookCard.setBid(rs.getInt(1));
				bookCard.setCid(rs.getInt(2));
				return bookCard;
			}
			
		}, bid, cid);
	}

	public void save(int bid, int cid) {
		String sql = "insert into t_book_card (bid, cid) values (?,?)";
		DbHelp.executeUpdate(sql, bid, cid);
	}

	public List<Book> findBorrowBookList(int cid) {
//		String sql = "select bookname,authorname,publishname,isbn from t_book as b, t_book_card as bc where b.id = bc.bid and cid = ? ";
		String sql = "select * from t_book where id in (select bid from t_book_card where cid = ?)";
		
		return DbHelp.queryForList(sql, new RowMapper<Book>(){

			@Override
			public Book mapperRow(ResultSet rs) throws SQLException {
				Book book = new Book();
				book.setId(rs.getInt(1));
				book.setBookName(rs.getString(2));
				book.setBookAuthor(rs.getString(3));
				book.setPublishName(rs.getString(4));
				book.setISBN(rs.getString(5));
				book.setTotalNum(rs.getInt(6));
				book.setCurrNum(rs.getInt(7));
				return book;
			}
			
		}, cid);
	}

	public void delete(int bid, int cid) {
		String sql = "delete from t_book_card where bid = ? and cid = ?";
		DbHelp.executeUpdate(sql, bid, cid);
	}
	
	
	
	
}
