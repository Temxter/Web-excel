package model;

import java.util.HashMap;
import java.util.Map;

public class ClassName {
    private static Map<Integer, String> classMap = new HashMap<>();

    static {
        classMap.put(1, "Денежные средства, драгоценные металлы и межбанковские операции");
        classMap.put(2, "Кредитные и иные активные операции с клиентами");
        classMap.put(3, "Счета по операциям клиентов");
        classMap.put(4, "Ценные бумаги");
        classMap.put(5, "Долгосрочные финансовые вложения в уставные фонды юридических лиц, основные средства и прочее имущество");
        classMap.put(6, "Прочие активы и прочие пассивы");
        classMap.put(7, "Собственный капитал банка");
        classMap.put(8, "Доходы банка");
        classMap.put(9, "Расходы банка");
    }

    public ClassName() { }

    public static String getClassName(Integer classNum) {
        if (classNum < 0 || classNum > 9) {
            return "КЛАСС  " + classNum;
        }
        return String.format("КЛАСС  %i  %s", classNum, classMap.get(classNum));
    }
}
