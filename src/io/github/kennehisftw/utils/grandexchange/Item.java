package io.github.kennehisftw.utils.grandexchange;

/**
 * Created by Kenneth on 6/14/2014.
 */

public class Item {

    private int id;
    private String icon;
    private String icon_large;
    private String type;
    private String name;
    private String description;
    private boolean membersitem;
    private Price prices;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    public String getLargeIcon() {
        return icon_large;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public boolean isMembersItem() {
        return membersitem;
    }

    public Price getPrices() {
        return prices;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", icon='" + icon + '\'' + ", icon_large='" + icon_large + '\'' + ", type='" + type + '\'' + ", name='" + name + '\'' + ", membersitem=" + membersitem + ", prices=" + prices + '}';
    }


    public class Price {
        private Current current;
        private Today today;
        private Day30 days30;
        private Day90 days90;
        private Day180 days180;
        private String exact;

        public Current getCurrent() {
            return current;
        }

        public Today getToday() {
            return today;
        }

        public Day30 getDay30() {
            return days30;
        }

        public Day90 getDay90() {
            return days90;
        }

        public Day180 getDay180() {
            return days180;
        }

        public String getExact() {
            return exact;
        }

        @Override
        public String toString() {
            return "Price{" + "current=" + current + ", today=" + today + ", day30=" + days30 + ", day90=" + days90 + ", day180=" + days180 + ", exact='" + exact + '\'' + '}';
        }
    }

    public class Current {

        private String trend;
        private String price;

        public String getTrend() {
            return trend;
        }

        public String getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Current{" + "trend='" + trend + '\'' + ", price='" + price + '\'' + '}';
        }
    }

    public class Today {

        private String trend;
        private String price;

        public String getTrend() {
            return trend;
        }

        public String getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Today{" + "trend='" + trend + '\'' + ", price='" + price + '\'' + '}';
        }
    }

    public class Day30 {

        private String trend;
        private String change;

        public String getTrend() {
            return trend;
        }

        public String getChange() {
            return change;
        }

        @Override
        public String toString() {
            return "Day30{" + "trend='" + trend + '\'' + ", change='" + change + '\'' + '}';
        }
    }

    public class Day90 {

        private String trend;
        private String change;

        public String getTrend() {
            return trend;
        }

        public String getChange() {
            return change;
        }

        @Override
        public String toString() {
            return "Day90{" + "trend='" + trend + '\'' + ", change='" + change + '\'' + '}';
        }
    }

    public class Day180 {

        private String trend;
        private String change;

        public String getTrend() {
            return trend;
        }

        public String getChange() {
            return change;
        }

        @Override
        public String toString() {
            return "Day180{" + "trend='" + trend + '\'' + ", change='" + change + '\'' +
                    '}';
        }
    }
}
