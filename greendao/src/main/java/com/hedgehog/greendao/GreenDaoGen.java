package com.hedgehog.greendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGen {

    public static final int DB_VERSION = 1;

    public static void main(String[] args) throws Exception {

        //Schema实模式的意思,有了Schema对象,就可以添加表(实体)了,化两个参数分别代表：数据库版本号与自动生成代码的包路径
        Schema schema = new Schema(DB_VERSION, "com.hedgehog.note.bean");
        schema.setDefaultJavaPackageDao("com.hedgehog.note.dao");


        // 模式（Schema）同时也拥有两个默认的 flags，分别用来标示 entity 是否是 activie 以及是否使用 keep sections。
        // schema2.enableActiveEntitiesByDefault();
        // schema2.enableKeepSectionsByDefault();

        addNote(schema);

        //最后我们将使用 DAOGenerator 类的 generateAll() 方法自动生成代码，此处你需要根据自己的情况更改输出目录（既之前创建的 java-gen)。
        new DaoGenerator().generateAll(schema, "app/src/main/java-gen");

    }

    public static void addNote(Schema schema) {
        // 一个实体（类）就关联到数据库中的一张表，此处表名为「Note」（既类名)
        Entity note = schema.addEntity("Note");
        // 你也可以重新给表命名
        // note.setTableName("Notes");

        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
        // 接下来你便可以设置表中的字段：
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }

}
