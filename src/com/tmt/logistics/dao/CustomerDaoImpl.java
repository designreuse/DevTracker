package com.tmt.logistics.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.common.Utils;
import com.tmt.logistics.bean.CustomerDetails;
import com.tmt.logistics.bean.DriverDetails;

public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	DataSource dataSource;

	public void insertCustomerData(CustomerDetails customerDetails) {
		System.out.println("customerDetails ::::: "+customerDetails.getParent_id());
		String sql = "INSERT INTO customerdetails "
				+ "(customerName, address, mobile, parent_id, handler_id) VALUES (?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { customerDetails.getCustName(), customerDetails.getAddress(),
						customerDetails.getMobile(), customerDetails.getParent_id(), Utils.generate5DigtRandomNum()});

	}
	
	public void insertDriverData(DriverDetails driverDetails) {
		String sql = "INSERT INTO driverdetails "
				+ "(vehicleNumber, driverName, driverMobile, driverLicense, driverAddress, handler_id) VALUES (?, ?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] {driverDetails.getVehicleNumber(), driverDetails.getDriverName(), driverDetails.getDriverMobile(),
						driverDetails.getDriverLicense(), driverDetails.getDriverAddress(), Utils.generate5DigtRandomNum()});
	}

	/*public List<CustomerDetails> getVehicleList() {
		String sql = "select * from user";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CustomerDetails> vehicleList = jdbcTemplate.query(sql, new RowMapper<CustomerDetails>() {
			@Override
			public CustomerDetails mapRow(ResultSet rs, int rowNum)throws SQLException {
				CustomerDetails vehicleDetail = new CustomerDetails();

				vehicleDetail.setVehicleNumber(rs.getString("contact_id"));
				vehicleDetail.setVehicleOwner(rs.getString("name"));
				vehicleDetail.setOwnerAddress(rs.getString("email"));
				vehicleDetail.setOwnerMobile(rs.getString("address"));
				vehicleDetail.setOwnerMobile1(rs.getString("address"));
				vehicleDetail.setOwnerMobile2(rs.getString("address"));
				vehicleDetail.setDriverName(rs.getString("address"));
				vehicleDetail.setDriverMobile(rs.getString("address"));
				vehicleDetail.setDriverLicense(rs.getString("address"));
				vehicleDetail.setDriverAddress(rs.getString("address"));
				
				return vehicleDetail;
			}

		});
		return vehicleList;
	}*/

	@Override
	public void deleteVehicleData(String id) {
		String sql = "delete from user where user_id=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);
	}

	/*@Override
	public void updateVehicleData(CustomerDetails vehicleAttachment) {

		String sql = "UPDATE user set first_name = ?,last_name = ?, gender = ?, city = ? where user_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { vehicleAttachment.getVehicleNumber(), vehicleAttachment.getVehicleOwner(),
						vehicleAttachment.getOwnerAddress(), vehicleAttachment.getOwnerMobile(),
						vehicleAttachment.getOwnerMobile1(), vehicleAttachment.getOwnerMobile2(),
						vehicleAttachment.getDriverName(), vehicleAttachment.getDriverMobile(),
						vehicleAttachment.getDriverLicense(), vehicleAttachment.getDriverAddress() });

	}*/

	/*@Override
	public CustomerDetails getVehicle(String id) {
		String sql = "select * from user where user_id= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query(sql, new ResultSetExtractor<CustomerDetails>() {

			@Override
			public CustomerDetails extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					CustomerDetails vehicleDetail = new CustomerDetails();

					vehicleDetail.setVehicleNumber(rs.getString("contact_id"));
					vehicleDetail.setVehicleOwner(rs.getString("name"));
					vehicleDetail.setOwnerAddress(rs.getString("email"));
					vehicleDetail.setOwnerMobile(rs.getString("address"));
					vehicleDetail.setOwnerMobile1(rs.getString("address"));
					vehicleDetail.setOwnerMobile2(rs.getString("address"));
					vehicleDetail.setDriverName(rs.getString("address"));
					vehicleDetail.setDriverMobile(rs.getString("address"));
					vehicleDetail.setDriverLicense(rs.getString("address"));
					vehicleDetail.setDriverAddress(rs.getString("address"));
					return vehicleDetail;
				}
				return null;
			}
		});
	}*/

}
