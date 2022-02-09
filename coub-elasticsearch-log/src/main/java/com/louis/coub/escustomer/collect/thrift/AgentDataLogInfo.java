package com.louis.coub.escustomer.collect.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2020-8-27")
public class AgentDataLogInfo implements org.apache.thrift.TBase<AgentDataLogInfo, AgentDataLogInfo._Fields>, java.io.Serializable, Cloneable, Comparable<AgentDataLogInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("AgentDataLogInfo");

  private static final org.apache.thrift.protocol.TField DATE_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("dateTime", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField LEVEL_FIELD_DESC = new org.apache.thrift.protocol.TField("level", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField LOG_FIELD_DESC = new org.apache.thrift.protocol.TField("log", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField SIZE_FIELD_DESC = new org.apache.thrift.protocol.TField("size", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField THREAD_FIELD_DESC = new org.apache.thrift.protocol.TField("thread", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField CLASS_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("className", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField TID_FIELD_DESC = new org.apache.thrift.protocol.TField("tid", org.apache.thrift.protocol.TType.STRING, (short)7);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new AgentDataLogInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new AgentDataLogInfoTupleSchemeFactory());
  }

  public long dateTime; // required
  public String level; // required
  public String log; // required
  public long size; // required
  public String thread; // optional
  public String className; // optional
  public String tid; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DATE_TIME((short)1, "dateTime"),
    LEVEL((short)2, "level"),
    LOG((short)3, "log"),
    SIZE((short)4, "size"),
    THREAD((short)5, "thread"),
    CLASS_NAME((short)6, "className"),
    TID((short)7, "tid");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // DATE_TIME
          return DATE_TIME;
        case 2: // LEVEL
          return LEVEL;
        case 3: // LOG
          return LOG;
        case 4: // SIZE
          return SIZE;
        case 5: // THREAD
          return THREAD;
        case 6: // CLASS_NAME
          return CLASS_NAME;
        case 7: // TID
          return TID;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __DATETIME_ISSET_ID = 0;
  private static final int __SIZE_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.THREAD,_Fields.CLASS_NAME,_Fields.TID};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DATE_TIME, new org.apache.thrift.meta_data.FieldMetaData("dateTime", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.LEVEL, new org.apache.thrift.meta_data.FieldMetaData("level", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LOG, new org.apache.thrift.meta_data.FieldMetaData("log", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SIZE, new org.apache.thrift.meta_data.FieldMetaData("size", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.THREAD, new org.apache.thrift.meta_data.FieldMetaData("thread", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CLASS_NAME, new org.apache.thrift.meta_data.FieldMetaData("className", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TID, new org.apache.thrift.meta_data.FieldMetaData("tid", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(AgentDataLogInfo.class, metaDataMap);
  }

  public AgentDataLogInfo() {
  }

  public AgentDataLogInfo(
    long dateTime,
    String level,
    String log,
    long size)
  {
    this();
    this.dateTime = dateTime;
    setDateTimeIsSet(true);
    this.level = level;
    this.log = log;
    this.size = size;
    setSizeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public AgentDataLogInfo(AgentDataLogInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    this.dateTime = other.dateTime;
    if (other.isSetLevel()) {
      this.level = other.level;
    }
    if (other.isSetLog()) {
      this.log = other.log;
    }
    this.size = other.size;
    if (other.isSetThread()) {
      this.thread = other.thread;
    }
    if (other.isSetClassName()) {
      this.className = other.className;
    }
    if (other.isSetTid()) {
      this.tid = other.tid;
    }
  }

  public AgentDataLogInfo deepCopy() {
    return new AgentDataLogInfo(this);
  }

  @Override
  public void clear() {
    setDateTimeIsSet(false);
    this.dateTime = 0;
    this.level = null;
    this.log = null;
    setSizeIsSet(false);
    this.size = 0;
    this.thread = null;
    this.className = null;
    this.tid = null;
  }

  public long getDateTime() {
    return this.dateTime;
  }

  public AgentDataLogInfo setDateTime(long dateTime) {
    this.dateTime = dateTime;
    setDateTimeIsSet(true);
    return this;
  }

  public void unsetDateTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __DATETIME_ISSET_ID);
  }

  /** Returns true if field dateTime is set (has been assigned a value) and false otherwise */
  public boolean isSetDateTime() {
    return EncodingUtils.testBit(__isset_bitfield, __DATETIME_ISSET_ID);
  }

  public void setDateTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __DATETIME_ISSET_ID, value);
  }

  public String getLevel() {
    return this.level;
  }

  public AgentDataLogInfo setLevel(String level) {
    this.level = level;
    return this;
  }

  public void unsetLevel() {
    this.level = null;
  }

  /** Returns true if field level is set (has been assigned a value) and false otherwise */
  public boolean isSetLevel() {
    return this.level != null;
  }

  public void setLevelIsSet(boolean value) {
    if (!value) {
      this.level = null;
    }
  }

  public String getLog() {
    return this.log;
  }

  public AgentDataLogInfo setLog(String log) {
    this.log = log;
    return this;
  }

  public void unsetLog() {
    this.log = null;
  }

  /** Returns true if field log is set (has been assigned a value) and false otherwise */
  public boolean isSetLog() {
    return this.log != null;
  }

  public void setLogIsSet(boolean value) {
    if (!value) {
      this.log = null;
    }
  }

  public long getSize() {
    return this.size;
  }

  public AgentDataLogInfo setSize(long size) {
    this.size = size;
    setSizeIsSet(true);
    return this;
  }

  public void unsetSize() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SIZE_ISSET_ID);
  }

  /** Returns true if field size is set (has been assigned a value) and false otherwise */
  public boolean isSetSize() {
    return EncodingUtils.testBit(__isset_bitfield, __SIZE_ISSET_ID);
  }

  public void setSizeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SIZE_ISSET_ID, value);
  }

  public String getThread() {
    return this.thread;
  }

  public AgentDataLogInfo setThread(String thread) {
    this.thread = thread;
    return this;
  }

  public void unsetThread() {
    this.thread = null;
  }

  /** Returns true if field thread is set (has been assigned a value) and false otherwise */
  public boolean isSetThread() {
    return this.thread != null;
  }

  public void setThreadIsSet(boolean value) {
    if (!value) {
      this.thread = null;
    }
  }

  public String getClassName() {
    return this.className;
  }

  public AgentDataLogInfo setClassName(String className) {
    this.className = className;
    return this;
  }

  public void unsetClassName() {
    this.className = null;
  }

  /** Returns true if field className is set (has been assigned a value) and false otherwise */
  public boolean isSetClassName() {
    return this.className != null;
  }

  public void setClassNameIsSet(boolean value) {
    if (!value) {
      this.className = null;
    }
  }

  public String getTid() {
    return this.tid;
  }

  public AgentDataLogInfo setTid(String tid) {
    this.tid = tid;
    return this;
  }

  public void unsetTid() {
    this.tid = null;
  }

  /** Returns true if field tid is set (has been assigned a value) and false otherwise */
  public boolean isSetTid() {
    return this.tid != null;
  }

  public void setTidIsSet(boolean value) {
    if (!value) {
      this.tid = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case DATE_TIME:
      if (value == null) {
        unsetDateTime();
      } else {
        setDateTime((Long)value);
      }
      break;

    case LEVEL:
      if (value == null) {
        unsetLevel();
      } else {
        setLevel((String)value);
      }
      break;

    case LOG:
      if (value == null) {
        unsetLog();
      } else {
        setLog((String)value);
      }
      break;

    case SIZE:
      if (value == null) {
        unsetSize();
      } else {
        setSize((Long)value);
      }
      break;

    case THREAD:
      if (value == null) {
        unsetThread();
      } else {
        setThread((String)value);
      }
      break;

    case CLASS_NAME:
      if (value == null) {
        unsetClassName();
      } else {
        setClassName((String)value);
      }
      break;

    case TID:
      if (value == null) {
        unsetTid();
      } else {
        setTid((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case DATE_TIME:
      return Long.valueOf(getDateTime());

    case LEVEL:
      return getLevel();

    case LOG:
      return getLog();

    case SIZE:
      return Long.valueOf(getSize());

    case THREAD:
      return getThread();

    case CLASS_NAME:
      return getClassName();

    case TID:
      return getTid();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case DATE_TIME:
      return isSetDateTime();
    case LEVEL:
      return isSetLevel();
    case LOG:
      return isSetLog();
    case SIZE:
      return isSetSize();
    case THREAD:
      return isSetThread();
    case CLASS_NAME:
      return isSetClassName();
    case TID:
      return isSetTid();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof AgentDataLogInfo)
      return this.equals((AgentDataLogInfo)that);
    return false;
  }

  public boolean equals(AgentDataLogInfo that) {
    if (that == null)
      return false;

    boolean this_present_dateTime = true;
    boolean that_present_dateTime = true;
    if (this_present_dateTime || that_present_dateTime) {
      if (!(this_present_dateTime && that_present_dateTime))
        return false;
      if (this.dateTime != that.dateTime)
        return false;
    }

    boolean this_present_level = true && this.isSetLevel();
    boolean that_present_level = true && that.isSetLevel();
    if (this_present_level || that_present_level) {
      if (!(this_present_level && that_present_level))
        return false;
      if (!this.level.equals(that.level))
        return false;
    }

    boolean this_present_log = true && this.isSetLog();
    boolean that_present_log = true && that.isSetLog();
    if (this_present_log || that_present_log) {
      if (!(this_present_log && that_present_log))
        return false;
      if (!this.log.equals(that.log))
        return false;
    }

    boolean this_present_size = true;
    boolean that_present_size = true;
    if (this_present_size || that_present_size) {
      if (!(this_present_size && that_present_size))
        return false;
      if (this.size != that.size)
        return false;
    }

    boolean this_present_thread = true && this.isSetThread();
    boolean that_present_thread = true && that.isSetThread();
    if (this_present_thread || that_present_thread) {
      if (!(this_present_thread && that_present_thread))
        return false;
      if (!this.thread.equals(that.thread))
        return false;
    }

    boolean this_present_className = true && this.isSetClassName();
    boolean that_present_className = true && that.isSetClassName();
    if (this_present_className || that_present_className) {
      if (!(this_present_className && that_present_className))
        return false;
      if (!this.className.equals(that.className))
        return false;
    }

    boolean this_present_tid = true && this.isSetTid();
    boolean that_present_tid = true && that.isSetTid();
    if (this_present_tid || that_present_tid) {
      if (!(this_present_tid && that_present_tid))
        return false;
      if (!this.tid.equals(that.tid))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_dateTime = true;
    list.add(present_dateTime);
    if (present_dateTime)
      list.add(dateTime);

    boolean present_level = true && (isSetLevel());
    list.add(present_level);
    if (present_level)
      list.add(level);

    boolean present_log = true && (isSetLog());
    list.add(present_log);
    if (present_log)
      list.add(log);

    boolean present_size = true;
    list.add(present_size);
    if (present_size)
      list.add(size);

    boolean present_thread = true && (isSetThread());
    list.add(present_thread);
    if (present_thread)
      list.add(thread);

    boolean present_className = true && (isSetClassName());
    list.add(present_className);
    if (present_className)
      list.add(className);

    boolean present_tid = true && (isSetTid());
    list.add(present_tid);
    if (present_tid)
      list.add(tid);

    return list.hashCode();
  }

  @Override
  public int compareTo(AgentDataLogInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetDateTime()).compareTo(other.isSetDateTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDateTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dateTime, other.dateTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLevel()).compareTo(other.isSetLevel());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLevel()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.level, other.level);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLog()).compareTo(other.isSetLog());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLog()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.log, other.log);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSize()).compareTo(other.isSetSize());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSize()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.size, other.size);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetThread()).compareTo(other.isSetThread());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetThread()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.thread, other.thread);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetClassName()).compareTo(other.isSetClassName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetClassName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.className, other.className);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTid()).compareTo(other.isSetTid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tid, other.tid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("AgentDataLogInfo(");
    boolean first = true;

    sb.append("dateTime:");
    sb.append(this.dateTime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("level:");
    if (this.level == null) {
      sb.append("null");
    } else {
      sb.append(this.level);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("log:");
    if (this.log == null) {
      sb.append("null");
    } else {
      sb.append(this.log);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("size:");
    sb.append(this.size);
    first = false;
    if (isSetThread()) {
      if (!first) sb.append(", ");
      sb.append("thread:");
      if (this.thread == null) {
        sb.append("null");
      } else {
        sb.append(this.thread);
      }
      first = false;
    }
    if (isSetClassName()) {
      if (!first) sb.append(", ");
      sb.append("className:");
      if (this.className == null) {
        sb.append("null");
      } else {
        sb.append(this.className);
      }
      first = false;
    }
    if (isSetTid()) {
      if (!first) sb.append(", ");
      sb.append("tid:");
      if (this.tid == null) {
        sb.append("null");
      } else {
        sb.append(this.tid);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    // alas, we cannot check 'dateTime' because it's a primitive and you chose the non-beans generator.
    if (level == null) {
      throw new TProtocolException("Required field 'level' was not present! Struct: " + toString());
    }
    if (log == null) {
      throw new TProtocolException("Required field 'log' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'size' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class AgentDataLogInfoStandardSchemeFactory implements SchemeFactory {
    public AgentDataLogInfoStandardScheme getScheme() {
      return new AgentDataLogInfoStandardScheme();
    }
  }

  private static class AgentDataLogInfoStandardScheme extends StandardScheme<AgentDataLogInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, AgentDataLogInfo struct) throws TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DATE_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.dateTime = iprot.readI64();
              struct.setDateTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // LEVEL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.level = iprot.readString();
              struct.setLevelIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // LOG
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.log = iprot.readString();
              struct.setLogIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SIZE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.size = iprot.readI64();
              struct.setSizeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // THREAD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.thread = iprot.readString();
              struct.setThreadIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // CLASS_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.className = iprot.readString();
              struct.setClassNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // TID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.tid = iprot.readString();
              struct.setTidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetDateTime()) {
        throw new TProtocolException("Required field 'dateTime' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetSize()) {
        throw new TProtocolException("Required field 'size' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, AgentDataLogInfo struct) throws TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(DATE_TIME_FIELD_DESC);
      oprot.writeI64(struct.dateTime);
      oprot.writeFieldEnd();
      if (struct.level != null) {
        oprot.writeFieldBegin(LEVEL_FIELD_DESC);
        oprot.writeString(struct.level);
        oprot.writeFieldEnd();
      }
      if (struct.log != null) {
        oprot.writeFieldBegin(LOG_FIELD_DESC);
        oprot.writeString(struct.log);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(SIZE_FIELD_DESC);
      oprot.writeI64(struct.size);
      oprot.writeFieldEnd();
      if (struct.thread != null) {
        if (struct.isSetThread()) {
          oprot.writeFieldBegin(THREAD_FIELD_DESC);
          oprot.writeString(struct.thread);
          oprot.writeFieldEnd();
        }
      }
      if (struct.className != null) {
        if (struct.isSetClassName()) {
          oprot.writeFieldBegin(CLASS_NAME_FIELD_DESC);
          oprot.writeString(struct.className);
          oprot.writeFieldEnd();
        }
      }
      if (struct.tid != null) {
        if (struct.isSetTid()) {
          oprot.writeFieldBegin(TID_FIELD_DESC);
          oprot.writeString(struct.tid);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class AgentDataLogInfoTupleSchemeFactory implements SchemeFactory {
    public AgentDataLogInfoTupleScheme getScheme() {
      return new AgentDataLogInfoTupleScheme();
    }
  }

  private static class AgentDataLogInfoTupleScheme extends TupleScheme<AgentDataLogInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, AgentDataLogInfo struct) throws TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI64(struct.dateTime);
      oprot.writeString(struct.level);
      oprot.writeString(struct.log);
      oprot.writeI64(struct.size);
      BitSet optionals = new BitSet();
      if (struct.isSetThread()) {
        optionals.set(0);
      }
      if (struct.isSetClassName()) {
        optionals.set(1);
      }
      if (struct.isSetTid()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetThread()) {
        oprot.writeString(struct.thread);
      }
      if (struct.isSetClassName()) {
        oprot.writeString(struct.className);
      }
      if (struct.isSetTid()) {
        oprot.writeString(struct.tid);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, AgentDataLogInfo struct) throws TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.dateTime = iprot.readI64();
      struct.setDateTimeIsSet(true);
      struct.level = iprot.readString();
      struct.setLevelIsSet(true);
      struct.log = iprot.readString();
      struct.setLogIsSet(true);
      struct.size = iprot.readI64();
      struct.setSizeIsSet(true);
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.thread = iprot.readString();
        struct.setThreadIsSet(true);
      }
      if (incoming.get(1)) {
        struct.className = iprot.readString();
        struct.setClassNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.tid = iprot.readString();
        struct.setTidIsSet(true);
      }
    }
  }

}