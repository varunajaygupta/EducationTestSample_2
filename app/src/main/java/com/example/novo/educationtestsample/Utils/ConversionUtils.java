package com.example.novo.educationtestsample.Utils;

/**
 * for value conversion related utils
 * Created by Hisham on 1/18/2016.
 */
public final class ConversionUtils {

    //TODO: Import apache String Utils
    private static final String TAG = ConversionUtils.class.getSimpleName();

    // private constructor
    private ConversionUtils() {
    }



/*    public static final List<Long> parseCommaSeparatedLongs(String value) {
        return parseDelimitedLongs(value, ",");

    }

    public static final List<String> parseCommaSeparatedStrings(String value) {
        return parseDelimitedStrings(value, ",");

    }

    public static final List<Long> parseDelimitedLongs(String value, String delimiter) {
        if (StringUtils.isBlank(value)) {
            return Collections.emptyList();
        }

        List<Long> numbers = new ArrayList<Long>();
        String[] parsed = value.split(",");

        for (String aNumber : parsed) {
            numbers.add(Long.parseLong(StringUtils.trimToEmpty(aNumber)));
        }

        return numbers;
    }

    public static final List<String> parseDelimitedStrings(String value, String delimiter) {
        if (StringUtils.isBlank(value)) {
            return Collections.emptyList();
        }

        List<String> values = new ArrayList<String>();
        String[] parsed = value.split(",");

        for (String aNumber : parsed) {
            values.add(StringUtils.trimToEmpty(aNumber));
        }

        return values;
    }

    public static final String buildDelimitedLongs(List<Long> numbers) {
        if (numbers == null || numbers.size() == 0) {
            return null;
        }

        StringBuilder str = new StringBuilder();
        int count = 1;
        for (Long aNumber : numbers) {
            str.append(aNumber.longValue());
            if ((count++) < numbers.size()) {
                str.append(",");
            }
        }
        return str.toString();
    }

    public static final String buildCommaDelimitedStrings(Set<String> numbers) {
        return buildDelimitedStrings(numbers, ",");
    }

    public static final String buildDelimitedStrings(Set<String> numbers, String delimiter) {
        if (numbers == null || numbers.size() == 0) {
            return null;
        }

        StringBuilder str = new StringBuilder();
        int count = 1;
        for (String aNumber : numbers) {
            str.append(aNumber);
            if ((count++) < numbers.size()) {
                str.append(delimiter);
            }
        }
        return str.toString();
    }

    public static final Date toDate(String str) throws Exception {
        if (StringUtils.isNotBlank(str)) {
            try {
                return ContextProvider.getContextField(ContextKeyEnum.DATE_FORMAT_MM_DD_YYYY, DateFormat.class).parse(str);
            } catch (Exception e) {
                throw e;
            }
        }
        return null;
    }

    public static final Date toTime(String str) throws Exception {
        if (StringUtils.isNotBlank(str)) {
            try {
                return ContextProvider.getContextField(ContextKeyEnum.DATE_FORMAT_HH_MM_SS, DateFormat.class).parse(str);
            } catch (Exception e) {
                throw new DataParseException("Cannot parse as date : ", str, e);
            }
        }
        return null;
    }


    public static final Date toDateTime(String str) throws BaseException {
        if (StringUtils.isNotBlank(str)) {
            try {
                return ContextProvider.getContextField(ContextKeyEnum.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, DateFormat.class).parse(str);
            } catch (ParseException e) {
                throw new DataParseException("Cannot parse as date : ", str, e);
            }
        }
        return null;
    }


    public static final Long toLong(String str) throws BaseException {
        if (StringUtils.isNotBlank(str)) {
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException e) {
                throw new DataParseException("Cannot parse as long : ", str, e);
            }
        }
        return null;
    }

    public static final Double toDouble(String str) throws BaseException {
        if (StringUtils.isNotBlank(str)) {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException e) {
                throw new DataParseException("Cannot parse as Double : ", str, e);
            }
        }
        return null;
    }

    public static final Integer toInteger(String str) throws BaseException {
        if (StringUtils.isNotBlank(str)) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                throw new DataParseException("Cannot parse as Integer : ", str, e);
            }
        }
        return null;
    }

    public static String toString(Long nbr) {
        if (nbr != null) {
            return nbr.toString();
        }
        return "";
    }

    public static String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return baos.toString("UTF-8");
    }

    public static String toString(Integer nbr) {
        if (nbr != null) {
            return nbr.toString();
        }
        return "";
    }

    public static String toString(Double nbr) {
        if (nbr != null) {
            return nbr.toString();
        }
        return "";
    }

    public static String toString(Date date) {
        if (date != null) {
            return ContextProvider.getContextField(ContextKeyEnum.DATE_FORMAT_MM_DD_YYYY, SimpleDateFormat.class).format(date);
        }
        return "";
    }

    public static String toDateTimeString(Date date) {
        if (date != null) {
            return ContextProvider.getContextField(ContextKeyEnum.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, SimpleDateFormat.class).format(date);
        }
        return "";
    }

    public static String toTimeString(Date date) {
        if (date != null) {
            return ContextProvider.getContextField(ContextKeyEnum.DATE_FORMAT_HH_MM_SS, SimpleDateFormat.class).format(date);
        }
        return "";
    }


    public static void shallowCopy(Object sourceObj, Object destObj, String[] fields) {
        int srcIndex = 0;
        int destIndex = 1;

        for (String fieldEntry : fields) {
            String[] split = fieldEntry.split(" ");
            String srcProperty;
            String destProperty;
            if (split.length == 2) {
                srcProperty = split[srcIndex];
                destProperty = split[destIndex];
            } else {
                srcProperty = fieldEntry;
                destProperty = fieldEntry;
            }
            try {
                BeanUtils.setProperty(destObj, destProperty, BeanUtils.getProperty(sourceObj, srcProperty));
            } catch (IllegalAccessException e) {
                log.debug("Exception while processing property ::: " + srcProperty);
                log.debug("Exception ::: " + e);
            } catch (InvocationTargetException e) {
                log.debug("Exception while processing property ::: " + srcProperty);
                log.debug("Exception ::: " + e);
            } catch (NoSuchMethodException e) {
                log.debug("Exception while processing property ::: " + srcProperty);
                log.debug("Exception ::: " + e);
            }
        }
    }

    public static void shallowCopyMatchingFields(Object src, Object target) {
        try {
            BeanUtils.copyProperties(target, src);
        } catch (IllegalAccessException e) {
            log.debug("Exception while processing shallow copy ::: ", e);
        } catch (InvocationTargetException e) {
            log.debug("Exception while processing shallow copy ::: ", e);
            log.debug("Exception ::: " + e);
        }
    }



    public static String dollarStringToAmount(String str) {
        if (str.startsWith("$")) {
            return str.substring(1).trim();
        }
        return str;
    }

    public static String toString(BigDecimal amount) {
        if (amount == null) {
            return "";
        }
        return amount.toString();
    }

    public static String toYYYY_MM_DD(Date taxFilerDOB) {
        SimpleDateFormat formatter = ContextProvider.getContextField(ContextKeyEnum.DATE_FORMAT_YYYY_MM_DD, SimpleDateFormat.class);
        return formatter.format(taxFilerDOB);
    }

    public static String truncateString(String val, int limit) {
        if (val == null) {
            return "";
        }
        if (val.length() <= limit) {
            return val;
        }
        int end = limit > val.length() ? val.length() : limit;
        return val.substring(0, end);
    }*/
}
