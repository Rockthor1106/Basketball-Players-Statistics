import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import model.DataManagement;

class DataManagementTest {

	private DataManagement dm;
	
	private void setUpScenary1() throws IOException {
		dm = new DataManagement();
		dm.clearAll();
	}
	
	private void setUpScenary2() throws IOException {
		dm = new DataManagement();
	}
	@Test
	public void isEmptyTest() throws IOException {
		setUpScenary1();
		assertEquals(true, dm.getPointsPerGame().isEmpty());
	}
	@Test
	public void isEmptyTest2() throws IOException {
		setUpScenary2();
		assertEquals(false, dm.getPointsPerGame().isEmpty());
	}
	@Test
	public void newPlayerTest() throws IOException {
		setUpScenary1();
		dm.addNewPlayer("Gilmar", "Amezquita", "Salchicha", 19, 56, 45, 23, 86, 56);
		assertEquals(false, dm.getAssistsPerGame().isEmpty());
		assertEquals("Gilmar", dm.getDataTable().getItem(200000).getName());
	}
	@Test
	public void balanceTest() throws IOException {
		setUpScenary1();
		dm.addNewPlayer("Gilmar", "Amezquita", "Salchicha", 19, 56, 45, 23, 86, 56);
		assertEquals(0, dm.getPointsPerGame().getBalance(dm.getPointsPerGame().getRoot()));
	}
	@Test
	public void sizeTest() throws IOException {
		setUpScenary1();
		dm.addNewPlayer("Gilmar", "Amezquita", "Salchicha", 19, 56, 45, 23, 86, 56);
		assertEquals(1, dm.getPointsPerGame().getSize());
	}
	@Test
	public void sizeTest2() throws IOException {
		setUpScenary2();
		dm.addNewPlayer("Gilmar", "Amezquita", "Salchicha", 19, 56, 45, 23, 86, 56);
		assertEquals(200001, dm.getPointsPerGame().getSize());
	}
	@Test
	public void getMinTest() throws IOException {
		setUpScenary1();
		dm.addNewPlayer("Gilmar", "Amezquita", "Salchicha", 19, 56, 45, 23, 86, 56);
		assertEquals(200000, dm.getPointsPerGame().getMin().getKey());
	}
	@Test
	public void getMinTest2() throws IOException {
		setUpScenary2();
		assertEquals(0, dm.getPointsPerGame().getMin().getData());
	}
	@Test
	public void getMaxTest() throws IOException {
		setUpScenary1();
		dm.addNewPlayer("Gilmar", "Amezquita", "Salchicha", 19, 56, 45, 23, 86, 56);
		assertEquals(200000, dm.getPointsPerGame().getMax().getKey());
	}
	@Test
	public void getMaxTest2() throws IOException {
		setUpScenary2();
		assertEquals(100, dm.getPointsPerGame().getMax().getData());
	}
}
