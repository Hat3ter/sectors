package db.migration;

import com.helmet.sectors.models.entity.Sector;
import com.helmet.sectors.utils.OptionParserHtml;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Migration fill sectors from html
 */
@Slf4j
public class V1_1_1__fill_sectors extends BaseJavaMigration {

	@Override
	public void migrate(Context context) throws Exception {

		Connection connection = context.getConnection();
		List<Sector> read = OptionParserHtml.read("index.html");
		String query = "insert into sectors (id,sector_name,agree_terms,parent_id) values (?,?,?,?);";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		saveToDb(read, preparedStatement);
		connection.prepareStatement("delete from sectors where agree_terms = false;").execute();
	}

	private void saveToDb(List<Sector> read, PreparedStatement preparedStatement) {

		read.forEach(sector -> {
			try {
				preparedStatement.setObject(1, sector.getId());
				preparedStatement.setString(2, sector.getSector());
				preparedStatement.setBoolean(3, true);
				preparedStatement.setObject(4, sector.getParentId());
				preparedStatement.execute();
			} catch (SQLException e) {
				log.warn("{}", e);
			}
			saveToDb(sector.getSectors(), preparedStatement);
		});
	}


}
