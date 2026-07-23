<script setup lang="ts">
import { siteContent } from '../data/siteContent'
</script>

<template>
  <section class="profile-hero page-reveal" aria-labelledby="profile-name">
    <img
      class="profile-portrait"
      :src="siteContent.profile.avatar"
      :alt="`${siteContent.profile.name} 的头像`"
      width="112"
      height="112"
    />

    <div class="profile-heading">
      <h1 id="profile-name">{{ siteContent.profile.name }}</h1>

      <div class="profile-meta">
        <span class="profile-meta-item">
          <svg aria-hidden="true" viewBox="0 0 24 24">
            <path d="M20 10c0 5-8 11-8 11S4 15 4 10a8 8 0 1 1 16 0Z" />
            <circle cx="12" cy="10" r="2.5" />
          </svg>
          {{ siteContent.profile.location }}
        </span>
        <a class="profile-meta-item" :href="siteContent.profile.github" target="_blank" rel="noreferrer">
          <svg aria-hidden="true" viewBox="0 0 24 24">
            <path d="M15 22v-4a4.8 4.8 0 0 0-1-3.5c3.3-.4 6.8-1.6 6.8-7.4A5.8 5.8 0 0 0 19.3 3 5.4 5.4 0 0 0 19.1.2S17.9-.2 15 1.7a13.4 13.4 0 0 0-6 0C6.1-.2 4.9.2 4.9.2A5.4 5.4 0 0 0 4.7 3a5.8 5.8 0 0 0-1.5 4.1c0 5.8 3.5 7 6.8 7.4A4.8 4.8 0 0 0 9 18v4" />
            <path d="M9 19c-3 .9-3-1.5-4.2-2" />
          </svg>
          GitHub
        </a>
      </div>
    </div>

    <a class="contact-pill" :href="`mailto:${siteContent.profile.email}`">
      <span class="status-dot" aria-hidden="true"></span>
      {{ siteContent.profile.contactLabel }}
    </a>
  </section>
</template>
