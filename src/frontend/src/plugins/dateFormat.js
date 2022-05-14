import Vue from "vue";
import dayjs from "dayjs";

const dateFormat = {
    install() {
        Vue.mixin({
            methods: {
                $formatDate(date) {
                    return dayjs(date).format('DD.MM.YYYY')
                }
            }
        })
    }
}

export default dateFormat;
