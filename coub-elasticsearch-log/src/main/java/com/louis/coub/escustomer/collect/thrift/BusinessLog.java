package com.louis.coub.escustomer.collect.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Collections;
import javax.annotation.Generated;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2020-8-27")
public class BusinessLog implements org.apache.thrift.TBase<BusinessLog, BusinessLog._Fields>, java.io.Serializable, Cloneable, Comparable<BusinessLog> {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("BusinessLog");

    private static final org.apache.thrift.protocol.TField KEY_FIELD_DESC = new org.apache.thrift.protocol.TField("key", org.apache.thrift.protocol.TType.STRING, (short)1);
    private static final org.apache.thrift.protocol.TField DATE_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("dateTime", org.apache.thrift.protocol.TType.I64, (short)2);
    private static final org.apache.thrift.protocol.TField FIELD_FIELD_DESC = new org.apache.thrift.protocol.TField("field", org.apache.thrift.protocol.TType.STRING, (short)3);
    private static final org.apache.thrift.protocol.TField MESSAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("message", org.apache.thrift.protocol.TType.STRING, (short)4);
    private static final org.apache.thrift.protocol.TField SIZE_FIELD_DESC = new org.apache.thrift.protocol.TField("size", org.apache.thrift.protocol.TType.I64, (short)5);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
        schemes.put(StandardScheme.class, new BusinessLogStandardSchemeFactory());
        schemes.put(TupleScheme.class, new BusinessLogTupleSchemeFactory());
    }

    public String key; // required
    public long dateTime; // required
    public String field; // required
    public String message; // required
    public long size; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
        KEY((short)1, "key"),
        DATE_TIME((short)2, "dateTime"),
        FIELD((short)3, "field"),
        MESSAGE((short)4, "message"),
        SIZE((short)5, "size");

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
                case 1: // KEY
                    return KEY;
                case 2: // DATE_TIME
                    return DATE_TIME;
                case 3: // FIELD
                    return FIELD;
                case 4: // MESSAGE
                    return MESSAGE;
                case 5: // SIZE
                    return SIZE;
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
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
        Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
        tmpMap.put(_Fields.KEY, new org.apache.thrift.meta_data.FieldMetaData("key", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        tmpMap.put(_Fields.DATE_TIME, new org.apache.thrift.meta_data.FieldMetaData("dateTime", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
        tmpMap.put(_Fields.FIELD, new org.apache.thrift.meta_data.FieldMetaData("field", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        tmpMap.put(_Fields.MESSAGE, new org.apache.thrift.meta_data.FieldMetaData("message", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        tmpMap.put(_Fields.SIZE, new org.apache.thrift.meta_data.FieldMetaData("size", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(BusinessLog.class, metaDataMap);
    }

    public BusinessLog() {
    }

    public BusinessLog(
            String key,
            long dateTime,
            String field,
            String message,
            long size)
    {
        this();
        this.key = key;
        this.dateTime = dateTime;
        setDateTimeIsSet(true);
        this.field = field;
        this.message = message;
        this.size = size;
        setSizeIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public BusinessLog(BusinessLog other) {
        __isset_bitfield = other.__isset_bitfield;
        if (other.isSetKey()) {
            this.key = other.key;
        }
        this.dateTime = other.dateTime;
        if (other.isSetField()) {
            this.field = other.field;
        }
        if (other.isSetMessage()) {
            this.message = other.message;
        }
        this.size = other.size;
    }

    public BusinessLog deepCopy() {
        return new BusinessLog(this);
    }

    @Override
    public void clear() {
        this.key = null;
        setDateTimeIsSet(false);
        this.dateTime = 0;
        this.field = null;
        this.message = null;
        setSizeIsSet(false);
        this.size = 0;
    }

    public String getKey() {
        return this.key;
    }

    public BusinessLog setKey(String key) {
        this.key = key;
        return this;
    }

    public void unsetKey() {
        this.key = null;
    }

    /** Returns true if field key is set (has been assigned a value) and false otherwise */
    public boolean isSetKey() {
        return this.key != null;
    }

    public void setKeyIsSet(boolean value) {
        if (!value) {
            this.key = null;
        }
    }

    public long getDateTime() {
        return this.dateTime;
    }

    public BusinessLog setDateTime(long dateTime) {
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

    public String getField() {
        return this.field;
    }

    public BusinessLog setField(String field) {
        this.field = field;
        return this;
    }

    public void unsetField() {
        this.field = null;
    }

    /** Returns true if field field is set (has been assigned a value) and false otherwise */
    public boolean isSetField() {
        return this.field != null;
    }

    public void setFieldIsSet(boolean value) {
        if (!value) {
            this.field = null;
        }
    }

    public String getMessage() {
        return this.message;
    }

    public BusinessLog setMessage(String message) {
        this.message = message;
        return this;
    }

    public void unsetMessage() {
        this.message = null;
    }

    /** Returns true if field message is set (has been assigned a value) and false otherwise */
    public boolean isSetMessage() {
        return this.message != null;
    }

    public void setMessageIsSet(boolean value) {
        if (!value) {
            this.message = null;
        }
    }

    public long getSize() {
        return this.size;
    }

    public BusinessLog setSize(long size) {
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

    public void setFieldValue(_Fields field, Object value) {
        switch (field) {
            case KEY:
                if (value == null) {
                    unsetKey();
                } else {
                    setKey((String)value);
                }
                break;

            case DATE_TIME:
                if (value == null) {
                    unsetDateTime();
                } else {
                    setDateTime((Long)value);
                }
                break;

            case FIELD:
                if (value == null) {
                    unsetField();
                } else {
                    setField((String)value);
                }
                break;

            case MESSAGE:
                if (value == null) {
                    unsetMessage();
                } else {
                    setMessage((String)value);
                }
                break;

            case SIZE:
                if (value == null) {
                    unsetSize();
                } else {
                    setSize((Long)value);
                }
                break;

        }
    }

    public Object getFieldValue(_Fields field) {
        switch (field) {
            case KEY:
                return getKey();

            case DATE_TIME:
                return Long.valueOf(getDateTime());

            case FIELD:
                return getField();

            case MESSAGE:
                return getMessage();

            case SIZE:
                return Long.valueOf(getSize());

        }
        throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }

        switch (field) {
            case KEY:
                return isSetKey();
            case DATE_TIME:
                return isSetDateTime();
            case FIELD:
                return isSetField();
            case MESSAGE:
                return isSetMessage();
            case SIZE:
                return isSetSize();
        }
        throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
        if (that == null)
            return false;
        if (that instanceof BusinessLog)
            return this.equals((BusinessLog)that);
        return false;
    }

    public boolean equals(BusinessLog that) {
        if (that == null)
            return false;

        boolean this_present_key = true && this.isSetKey();
        boolean that_present_key = true && that.isSetKey();
        if (this_present_key || that_present_key) {
            if (!(this_present_key && that_present_key))
                return false;
            if (!this.key.equals(that.key))
                return false;
        }

        boolean this_present_dateTime = true;
        boolean that_present_dateTime = true;
        if (this_present_dateTime || that_present_dateTime) {
            if (!(this_present_dateTime && that_present_dateTime))
                return false;
            if (this.dateTime != that.dateTime)
                return false;
        }

        boolean this_present_field = true && this.isSetField();
        boolean that_present_field = true && that.isSetField();
        if (this_present_field || that_present_field) {
            if (!(this_present_field && that_present_field))
                return false;
            if (!this.field.equals(that.field))
                return false;
        }

        boolean this_present_message = true && this.isSetMessage();
        boolean that_present_message = true && that.isSetMessage();
        if (this_present_message || that_present_message) {
            if (!(this_present_message && that_present_message))
                return false;
            if (!this.message.equals(that.message))
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

        return true;
    }

    @Override
    public int hashCode() {
        List<Object> list = new ArrayList<Object>();

        boolean present_key = true && (isSetKey());
        list.add(present_key);
        if (present_key)
            list.add(key);

        boolean present_dateTime = true;
        list.add(present_dateTime);
        if (present_dateTime)
            list.add(dateTime);

        boolean present_field = true && (isSetField());
        list.add(present_field);
        if (present_field)
            list.add(field);

        boolean present_message = true && (isSetMessage());
        list.add(present_message);
        if (present_message)
            list.add(message);

        boolean present_size = true;
        list.add(present_size);
        if (present_size)
            list.add(size);

        return list.hashCode();
    }

    @Override
    public int compareTo(BusinessLog other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }

        int lastComparison = 0;

        lastComparison = Boolean.valueOf(isSetKey()).compareTo(other.isSetKey());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetKey()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.key, other.key);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
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
        lastComparison = Boolean.valueOf(isSetField()).compareTo(other.isSetField());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetField()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.field, other.field);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetMessage()).compareTo(other.isSetMessage());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetMessage()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.message, other.message);
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
        StringBuilder sb = new StringBuilder("BusinessLog(");
        boolean first = true;

        sb.append("key:");
        if (this.key == null) {
            sb.append("null");
        } else {
            sb.append(this.key);
        }
        first = false;
        if (!first) sb.append(", ");
        sb.append("dateTime:");
        sb.append(this.dateTime);
        first = false;
        if (!first) sb.append(", ");
        sb.append("field:");
        if (this.field == null) {
            sb.append("null");
        } else {
            sb.append(this.field);
        }
        first = false;
        if (!first) sb.append(", ");
        sb.append("message:");
        if (this.message == null) {
            sb.append("null");
        } else {
            sb.append(this.message);
        }
        first = false;
        if (!first) sb.append(", ");
        sb.append("size:");
        sb.append(this.size);
        first = false;
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        // check for required fields
        if (key == null) {
            throw new TProtocolException("Required field 'key' was not present! Struct: " + toString());
        }
        // alas, we cannot check 'dateTime' because it's a primitive and you chose the non-beans generator.
        if (field == null) {
            throw new TProtocolException("Required field 'field' was not present! Struct: " + toString());
        }
        if (message == null) {
            throw new TProtocolException("Required field 'message' was not present! Struct: " + toString());
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

    private static class BusinessLogStandardSchemeFactory implements SchemeFactory {
        public BusinessLogStandardScheme getScheme() {
            return new BusinessLogStandardScheme();
        }
    }

    private static class BusinessLogStandardScheme extends StandardScheme<BusinessLog> {

        public void read(org.apache.thrift.protocol.TProtocol iprot, BusinessLog struct) throws TException {
            org.apache.thrift.protocol.TField schemeField;
            iprot.readStructBegin();
            while (true)
            {
                schemeField = iprot.readFieldBegin();
                if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
                    break;
                }
                switch (schemeField.id) {
                    case 1: // KEY
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.key = iprot.readString();
                            struct.setKeyIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 2: // DATE_TIME
                        if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
                            struct.dateTime = iprot.readI64();
                            struct.setDateTimeIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 3: // FIELD
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.field = iprot.readString();
                            struct.setFieldIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 4: // MESSAGE
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.message = iprot.readString();
                            struct.setMessageIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 5: // SIZE
                        if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
                            struct.size = iprot.readI64();
                            struct.setSizeIsSet(true);
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

        public void write(org.apache.thrift.protocol.TProtocol oprot, BusinessLog struct) throws TException {
            struct.validate();

            oprot.writeStructBegin(STRUCT_DESC);
            if (struct.key != null) {
                oprot.writeFieldBegin(KEY_FIELD_DESC);
                oprot.writeString(struct.key);
                oprot.writeFieldEnd();
            }
            oprot.writeFieldBegin(DATE_TIME_FIELD_DESC);
            oprot.writeI64(struct.dateTime);
            oprot.writeFieldEnd();
            if (struct.field != null) {
                oprot.writeFieldBegin(FIELD_FIELD_DESC);
                oprot.writeString(struct.field);
                oprot.writeFieldEnd();
            }
            if (struct.message != null) {
                oprot.writeFieldBegin(MESSAGE_FIELD_DESC);
                oprot.writeString(struct.message);
                oprot.writeFieldEnd();
            }
            oprot.writeFieldBegin(SIZE_FIELD_DESC);
            oprot.writeI64(struct.size);
            oprot.writeFieldEnd();
            oprot.writeFieldStop();
            oprot.writeStructEnd();
        }

    }

    private static class BusinessLogTupleSchemeFactory implements SchemeFactory {
        public BusinessLogTupleScheme getScheme() {
            return new BusinessLogTupleScheme();
        }
    }

    private static class BusinessLogTupleScheme extends TupleScheme<BusinessLog> {

        @Override
        public void write(org.apache.thrift.protocol.TProtocol prot, BusinessLog struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol) prot;
            oprot.writeString(struct.key);
            oprot.writeI64(struct.dateTime);
            oprot.writeString(struct.field);
            oprot.writeString(struct.message);
            oprot.writeI64(struct.size);
        }

        @Override
        public void read(org.apache.thrift.protocol.TProtocol prot, BusinessLog struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol) prot;
            struct.key = iprot.readString();
            struct.setKeyIsSet(true);
            struct.dateTime = iprot.readI64();
            struct.setDateTimeIsSet(true);
            struct.field = iprot.readString();
            struct.setFieldIsSet(true);
            struct.message = iprot.readString();
            struct.setMessageIsSet(true);
            struct.size = iprot.readI64();
            struct.setSizeIsSet(true);
        }
    }

}
