<template>
  <div>
    <el-table :data="schools" style="width: 100%" row-key="id">
      <el-table-column prop="id" label="ID" width="50"></el-table-column>
      <el-table-column prop="school_id" label="School ID" width="100"></el-table-column>
      <el-table-column prop="name" label="School Name"></el-table-column>
      <el-table-column prop="location" label="Location"></el-table-column>
      <el-table-column prop="description" label="Description"></el-table-column>
      <el-table-column prop="contact_phone" label="Contact Phone"></el-table-column>
      <el-table-column prop="contact_email" label="Contact Email"></el-table-column>
      <el-table-column prop="website" label="Website">
        <template #default="{ row }">
          <a :href="row.website" target="_blank">{{ row.website }}</a>
        </template>
      </el-table-column>
      <el-table-column label="Actions" width="180">
        <template #default="{ row }">
          <el-button type="text" @click="searchSchool(row)">Search</el-button>
          <el-button type="text" @click="applyForSchool(row)">Apply</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { ref } from 'vue';
import 'element-plus/dist/index.css';

export default {
  setup() {
    const schools = ref([
      {
        id: 1,
        school_id: 'SC001',
        name: 'Tsinghua University',
        location: 'Haidian District, Beijing',
        description: 'Top comprehensive university in China',
        contact_phone: '010-62793001',
        contact_email: 'admission@tsinghua.edu.cn',
        website: 'https://www.tsinghua.edu.cn'
      },
      {
        id: 2,
        school_id: 'SC002',
        name: 'Peking University',
        location: 'Haidian District, Beijing',
        description: 'Top comprehensive university in China',
        contact_phone: '010-62751354',
        contact_email: 'zsb@pku.edu.cn',
        website: 'https://www.pku.edu.cn'
      },
      {
        id: 3,
        school_id: 'SC003',
        name: 'Fudan University',
        location: 'Yangpu District, Shanghai',
        description: 'Top comprehensive university in China',
        contact_phone: '021-65642222',
        contact_email: 'admission@fudan.edu.cn',
        website: 'https://www.fudan.edu.cn'
      },
      {
        id: 4,
        school_id: 'SC004',
        name: 'Shanghai Jiao Tong University',
        location: 'Minhang District, Shanghai',
        description: 'Top comprehensive university in China',
        contact_phone: '021-34200000',
        contact_email: 'zsb@sjtu.edu.cn',
        website: 'https://www.sjtu.edu.cn'
      },
      {
        id: 5,
        school_id: 'SC005',
        name: 'Zhejiang University',
        location: 'Hangzhou, Zhejiang Province',
        description: 'Top comprehensive university in China',
        contact_phone: '0571-87951006',
        contact_email: 'zsc@zju.edu.cn',
        website: 'https://www.zju.edu.cn'
      },
      {
        id: 6,
        school_id: 'SC006',
        name: 'Nanjing University',
        location: 'Nanjing, Jiangsu Province',
        description: 'Top comprehensive university in China',
        contact_phone: '025-83593186',
        contact_email: 'bkzs@nju.edu.cn',
        website: 'https://www.nju.edu.cn'
      }
    ]);

    const searchSchools = () => {
      console.log('Search school information');
      // Add search logic here
    };

    const apply = () => {
      console.log('Submit application');
      // Add application logic here
    };

    const searchSchool = (school) => {
      console.log('Search school details:', school);
      // Add school details search logic here
    };

    const applyForSchool = (school) => {
      console.log('Apply to school:', school);
      // Add school application logic here
    };

    return {
      schools,
      searchSchools,
      apply,
      searchSchool,
      applyForSchool
    };
  }
};
</script>

<style>
/* Add some basic styles */
.container {
  padding: 20px;
}

.button-group {
  margin-bottom: 20px;
}
</style>