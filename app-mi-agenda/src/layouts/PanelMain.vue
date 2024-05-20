<template>
    <div class="app-container">
        <header class="sticky-header">
            <app-header></app-header>
        </header>
        <main class="content-main">
            <router-view></router-view>
        </main>
        <footer class="fixed-footer">
            <app-footer></app-footer>
        </footer>
    </div>
</template>

<script>
import AppFooter from '../components/AppFooter.vue'
import AppHeader from '../components/AppHeader.vue'

export default {
    name: 'panelMain',

    components:{
        AppFooter,
        AppHeader,

    }

}
</script>
