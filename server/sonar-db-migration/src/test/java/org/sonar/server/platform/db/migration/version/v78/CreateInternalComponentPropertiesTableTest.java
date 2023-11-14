/*
 * SonarQube
 * Copyright (C) 2009-2019 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.server.platform.db.migration.version.v78;

import java.sql.SQLException;
import java.sql.Types;
import org.junit.Rule;
import org.junit.Test;
import org.sonar.db.CoreDbTester;

public class CreateInternalComponentPropertiesTableTest {

  private static final String TABLE = "internal_component_props";

  @Rule
  public final CoreDbTester db = CoreDbTester.createEmpty();

  private CreateInternalComponentPropertiesTable underTest = new CreateInternalComponentPropertiesTable(db.database());

  @Test
  public void creates_table() throws SQLException {
    underTest.execute();

    checkTable();
  }

  @Test
  public void migration_is_reentrant() throws SQLException {
    underTest.execute();
    underTest.execute();

    checkTable();
  }

  private void checkTable() {
    db.assertColumnDefinition(TABLE, "uuid", Types.VARCHAR, 40, false);
    db.assertColumnDefinition(TABLE, "component_uuid", Types.VARCHAR, 50, false);
    db.assertColumnDefinition(TABLE, "kee", Types.VARCHAR, 512, false);
    db.assertColumnDefinition(TABLE, "value", Types.VARCHAR, 4000, true);
    db.assertColumnDefinition(TABLE, "updated_at", Types.BIGINT, null, false);
    db.assertColumnDefinition(TABLE, "created_at", Types.BIGINT, null, false);

    db.assertUniqueIndex(TABLE, "unique_component_uuid_kee", "component_uuid", "kee");
  }
}
