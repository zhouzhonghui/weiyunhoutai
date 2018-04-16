package io.renren.modules.news.entity;

import java.util.ArrayList;
import java.util.List;

public class NewsAttachmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NewsAttachmentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAttatypeIsNull() {
            addCriterion("attatype is null");
            return (Criteria) this;
        }

        public Criteria andAttatypeIsNotNull() {
            addCriterion("attatype is not null");
            return (Criteria) this;
        }

        public Criteria andAttatypeEqualTo(String value) {
            addCriterion("attatype =", value, "attatype");
            return (Criteria) this;
        }

        public Criteria andAttatypeNotEqualTo(String value) {
            addCriterion("attatype <>", value, "attatype");
            return (Criteria) this;
        }

        public Criteria andAttatypeGreaterThan(String value) {
            addCriterion("attatype >", value, "attatype");
            return (Criteria) this;
        }

        public Criteria andAttatypeGreaterThanOrEqualTo(String value) {
            addCriterion("attatype >=", value, "attatype");
            return (Criteria) this;
        }

        public Criteria andAttatypeLessThan(String value) {
            addCriterion("attatype <", value, "attatype");
            return (Criteria) this;
        }

        public Criteria andAttatypeLessThanOrEqualTo(String value) {
            addCriterion("attatype <=", value, "attatype");
            return (Criteria) this;
        }

        public Criteria andAttatypeLike(String value) {
            addCriterion("attatype like", value, "attatype");
            return (Criteria) this;
        }

        public Criteria andAttatypeNotLike(String value) {
            addCriterion("attatype not like", value, "attatype");
            return (Criteria) this;
        }

        public Criteria andAttatypeIn(List<String> values) {
            addCriterion("attatype in", values, "attatype");
            return (Criteria) this;
        }

        public Criteria andAttatypeNotIn(List<String> values) {
            addCriterion("attatype not in", values, "attatype");
            return (Criteria) this;
        }

        public Criteria andAttatypeBetween(String value1, String value2) {
            addCriterion("attatype between", value1, value2, "attatype");
            return (Criteria) this;
        }

        public Criteria andAttatypeNotBetween(String value1, String value2) {
            addCriterion("attatype not between", value1, value2, "attatype");
            return (Criteria) this;
        }

        public Criteria andUrlpathIsNull() {
            addCriterion("urlpath is null");
            return (Criteria) this;
        }

        public Criteria andUrlpathIsNotNull() {
            addCriterion("urlpath is not null");
            return (Criteria) this;
        }

        public Criteria andUrlpathEqualTo(String value) {
            addCriterion("urlpath =", value, "urlpath");
            return (Criteria) this;
        }

        public Criteria andUrlpathNotEqualTo(String value) {
            addCriterion("urlpath <>", value, "urlpath");
            return (Criteria) this;
        }

        public Criteria andUrlpathGreaterThan(String value) {
            addCriterion("urlpath >", value, "urlpath");
            return (Criteria) this;
        }

        public Criteria andUrlpathGreaterThanOrEqualTo(String value) {
            addCriterion("urlpath >=", value, "urlpath");
            return (Criteria) this;
        }

        public Criteria andUrlpathLessThan(String value) {
            addCriterion("urlpath <", value, "urlpath");
            return (Criteria) this;
        }

        public Criteria andUrlpathLessThanOrEqualTo(String value) {
            addCriterion("urlpath <=", value, "urlpath");
            return (Criteria) this;
        }

        public Criteria andUrlpathLike(String value) {
            addCriterion("urlpath like", value, "urlpath");
            return (Criteria) this;
        }

        public Criteria andUrlpathNotLike(String value) {
            addCriterion("urlpath not like", value, "urlpath");
            return (Criteria) this;
        }

        public Criteria andUrlpathIn(List<String> values) {
            addCriterion("urlpath in", values, "urlpath");
            return (Criteria) this;
        }

        public Criteria andUrlpathNotIn(List<String> values) {
            addCriterion("urlpath not in", values, "urlpath");
            return (Criteria) this;
        }

        public Criteria andUrlpathBetween(String value1, String value2) {
            addCriterion("urlpath between", value1, value2, "urlpath");
            return (Criteria) this;
        }

        public Criteria andUrlpathNotBetween(String value1, String value2) {
            addCriterion("urlpath not between", value1, value2, "urlpath");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andAttaidIsNull() {
            addCriterion("attaid is null");
            return (Criteria) this;
        }

        public Criteria andAttaidIsNotNull() {
            addCriterion("attaid is not null");
            return (Criteria) this;
        }

        public Criteria andAttaidEqualTo(String value) {
            addCriterion("attaid =", value, "attaid");
            return (Criteria) this;
        }

        public Criteria andAttaidNotEqualTo(String value) {
            addCriterion("attaid <>", value, "attaid");
            return (Criteria) this;
        }

        public Criteria andAttaidGreaterThan(String value) {
            addCriterion("attaid >", value, "attaid");
            return (Criteria) this;
        }

        public Criteria andAttaidGreaterThanOrEqualTo(String value) {
            addCriterion("attaid >=", value, "attaid");
            return (Criteria) this;
        }

        public Criteria andAttaidLessThan(String value) {
            addCriterion("attaid <", value, "attaid");
            return (Criteria) this;
        }

        public Criteria andAttaidLessThanOrEqualTo(String value) {
            addCriterion("attaid <=", value, "attaid");
            return (Criteria) this;
        }

        public Criteria andAttaidLike(String value) {
            addCriterion("attaid like", value, "attaid");
            return (Criteria) this;
        }

        public Criteria andAttaidNotLike(String value) {
            addCriterion("attaid not like", value, "attaid");
            return (Criteria) this;
        }

        public Criteria andAttaidIn(List<String> values) {
            addCriterion("attaid in", values, "attaid");
            return (Criteria) this;
        }

        public Criteria andAttaidNotIn(List<String> values) {
            addCriterion("attaid not in", values, "attaid");
            return (Criteria) this;
        }

        public Criteria andAttaidBetween(String value1, String value2) {
            addCriterion("attaid between", value1, value2, "attaid");
            return (Criteria) this;
        }

        public Criteria andAttaidNotBetween(String value1, String value2) {
            addCriterion("attaid not between", value1, value2, "attaid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}