package com.zhou;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 内存分页，字段排序
 * @Author: YanMei.Li
 * @Date: Create in 10:59 2021/11/10
 * @Modified By:
 */
public class MemoryPagination {

  /**
   * 给list的每个属性都指定是升序还是降序
   * list元素的属性可以是数字（byte、short、int、long、float、double等，支持正数、负数、0）、char、String、java.util.Date
   *
   * @param list        数据集合
   * @param pageNum     第几页
   * @param pageSize    每页多少条
   * @param isSort      是否排序
   * @param sortNameArr 参数数组
   * @param sortArr     每个属性对应的升降序数组， true升序，false降序
   */
  public static <E> List<E> sortMemoryPagination(List<E> list, int pageNum, int pageSize, final boolean isSort,
      final String[] sortNameArr, final boolean[] sortArr) {
    if (isSort) {
      if (sortNameArr.length != sortArr.length) {
        throw new RuntimeException("属性数组元素个数和升降序数组元素个数不相等");
      }
      Collections.sort(list, new Comparator<E>() {
        @Override
        public int compare(E a, E b) {
          int ret = 0;
          try {
            for (int i = 0; i < sortNameArr.length; i++) {
              ret = MemoryPagination.compareObject(sortNameArr[i], sortArr[i], a, b);
              if (0 != ret) {
                break;
              }
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
          return ret;
        }
      });

    }
    return pagination(list, pageNum, pageSize);
  }

  /**
   * 内存分页
   *
   * @param records  待分页的数据
   * @param pageNum  当前页码
   * @param pageSize 每页显示的条数
   * @return 分页之后的数据
   */
  private static <T> List<T> pagination(List<T> records, int pageNum, int pageSize) {

    if (records == null || records.size() == 0) {
      return Collections.emptyList();
    }

    int totalCount = records.size();
    int remainder = totalCount % pageSize;
    int pageCount = (remainder > 0) ? totalCount / pageSize + 1 : totalCount / pageSize;
    if (remainder == 0) {
      return records.stream().skip((pageNum - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    } else {
      if (pageNum == pageCount) {
        return records.stream().skip((pageNum - 1) * pageSize).limit(totalCount).collect(Collectors.toList());
      } else {
        return records.stream().skip((pageNum - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
      }
    }
  }

  /**
   * 对2个对象按照指定属性名称进行排序
   *
   * @param sortname 属性名称
   * @param isAsc    true升序，false降序
   * @param a
   * @param b
   * @return
   * @throws Exception
   */
  private static <E> int compareObject(final String sortname, final boolean isAsc, E a, E b) throws Exception {
    int ret;
    Object value1 = MemoryPagination.forceGetFieldValue(a, sortname);
    Object value2 = MemoryPagination.forceGetFieldValue(b, sortname);
    String str1 = value1.toString();
    String str2 = value2.toString();
    if (value1 instanceof Number && value2 instanceof Number) {
      int maxlen = Math.max(str1.length(), str2.length());
      str1 = MemoryPagination.addZero2Str((Number) value1, maxlen);
      str2 = MemoryPagination.addZero2Str((Number) value2, maxlen);
    } else if (value1 instanceof java.sql.Date && value2 instanceof java.sql.Date) {
      long time1 = ((java.sql.Date) value1).getTime();
      long time2 = ((Date) value2).getTime();
      int maxlen = Long.toString(Math.max(time1, time2)).length();
      str1 = MemoryPagination.addZero2Str(time1, maxlen);
      str2 = MemoryPagination.addZero2Str(time2, maxlen);
    }
    if (isAsc) {
      ret = str1.compareTo(str2);
    } else {
      ret = str2.compareTo(str1);
    }
    return ret;
  }

  /**
   * 获取指定对象的指定属性值（去除private,protected的限制）
   *
   * @param obj       属性名称所在的对象
   * @param fieldName 属性名称
   * @return
   * @throws Exception
   */
  public static Object forceGetFieldValue(Object obj, String fieldName) throws Exception {
    Field field = obj.getClass().getDeclaredField(fieldName);
    Object object = null;
    boolean accessible = field.isAccessible();
    if (!accessible) {
      // 如果是private,protected修饰的属性，需要修改为可以访问的
      field.setAccessible(true);
      object = field.get(obj);
      // 还原private,protected属性的访问性质
      field.setAccessible(accessible);
      return object;
    }
    object = field.get(obj);
    return object;
  }

  /**
   * 给数字对象按照指定长度在左侧补0.
   * <p>
   * 使用案例: addZero2Str(11,4) 返回 "0011", addZero2Str(-18,6)返回 "-000018"
   *
   * @param numObj 数字对象
   * @param length 指定的长度
   * @return
   */
  public static String addZero2Str(Number numObj, int length) {
    NumberFormat nf = NumberFormat.getInstance();
    // 设置是否使用分组
    nf.setGroupingUsed(false);
    // 设置最大整数位数
    nf.setMaximumIntegerDigits(length);
    // 设置最小整数位数
    nf.setMinimumIntegerDigits(length);
    return nf.format(numObj);
  }

  public static void main(String[] args) {
    List<SysDict> list = new ArrayList<>();
    list.add(new SysDict(1, "test1", "1", 1, 3.32));
    list.add(new SysDict(3, "test3", "3", 3, 4.13));
    list.add(new SysDict(2, "test2", "11", 2, 4.11));
    list.add(new SysDict(4, "test4", "4", 4, 23.23));
    list.add(new SysDict(5, "test5", "5", 5, 0.23));
    list.add(new SysDict(6, "test6", "22", 6, 0.23));
    list.add(new SysDict(7, "test7", "3", 7, 0.23));
    list.add(new SysDict(8, "test8", "0.2", 8, 0.23));


        String[] sortnameArr = {"ss","sort"};
        boolean[] typeArr = {false,false};
    //String[] sortnameArr = {"name"};
    //boolean[] typeArr = {false};
    List<SysDict> ss = sortMemoryPagination(list, 1, 5, true, sortnameArr, typeArr);
    System.out.println(ss.toString());

  }

}


class SysDict {
  private Integer id;
  private String name;
  private String value;
  private Integer sort;
  private Double ss;

  public SysDict(Integer id, String name, String value, Integer sort, Double ss) {
    this.id = id;
    this.name = name;
    this.value = value;
    this.sort = sort;
    this.ss = ss;
  }

  public Double getSs() {
    return ss;
  }

  public void setSs(Double ss) {
    this.ss = ss;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Integer getSort() {
    return sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
  }


  @Override
  public String toString() {
    return "SysDict{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", value='" + value + '\'' +
        ", sort=" + sort +
        ", ss=" + ss +
        '}';
  }
}
