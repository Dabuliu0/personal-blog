<script setup lang="ts">
import { RouterLink } from 'vue-router'
import { siteContent } from '../data/siteContent'
</script>

<template>
  <main class="inner-page page-reveal">
    <RouterLink class="back-link" to="/">
      <span aria-hidden="true">←</span>
      Back
    </RouterLink>

    <div class="about-layout">
      <article class="about-article">
        <header class="inner-heading about-heading">
          <h1>About</h1>
          <p>{{ siteContent.profile.intro }}</p>
        </header>

        <section v-for="section in siteContent.aboutSections" :id="section.id" :key="section.id">
          <h2>{{ section.title }}</h2>
          <p v-for="paragraph in section.paragraphs" :key="paragraph">{{ paragraph }}</p>
        </section>

        <section id="principles">
          <h2>Writing Principles</h2>
          <ol class="principle-list">
            <li v-for="principle in siteContent.writingPrinciples" :key="principle">
              {{ principle }}
            </li>
          </ol>
        </section>
      </article>

      <aside class="about-toc" aria-label="页面目录">
        <h2>TABLE OF CONTENTS</h2>
        <nav>
          <a v-for="section in siteContent.aboutSections" :key="section.id" :href="`#${section.id}`">
            {{ section.title }}
          </a>
          <a href="#principles">Writing Principles</a>
        </nav>
      </aside>
    </div>
  </main>
</template>
