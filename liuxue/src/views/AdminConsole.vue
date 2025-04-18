<template>
  <div class="app-container">
    <div class="header-buttons">
      <el-button
          type="primary"
          :class="{ 'active-button': activeTab === 'student' }"
          @click="activeTab = 'student'"
      >
        Student Management
      </el-button>
      <el-button
          type="primary"
          :class="{ 'active-button': activeTab === 'school' }"
          @click="activeTab = 'school'"
      >
        School Management
      </el-button>
      <el-button
          type="primary"
          :class="{ 'active-button': activeTab === 'application' }"
          @click="activeTab = 'application'"
      >
        Application Management
      </el-button>
    </div>

    <!-- Student Management Table -->
    <div v-if="activeTab === 'student'" class="table-container">
      <el-table
          :data="studentPaginatedData"
          style="width: 100%"
          stripe
          border
          highlight-current-row
      >
        <el-table-column
            label="ID"
            prop="id"
            width="80"
            sortable
            align="center"
        />
        <el-table-column
            label="Student ID"
            prop="student_id"
            width="120"
            align="center"
        />
        <el-table-column
            label="Name"
            prop="name"
            width="120"
            align="center"
        />
        <el-table-column
            label="Gender"
            prop="gender"
            width="80"
            align="center"
        />
        <el-table-column
            label="Birth Date"
            prop="birth_date"
            width="120"
            sortable
            align="center"
        />
        <el-table-column
            label="Phone"
            prop="contact_phone"
            width="150"
            align="center"
        />
        <el-table-column
            label="Email"
            prop="contact_email"
            width="180"
            align="center"
        />
        <el-table-column
            label="Address"
            prop="address"
            show-overflow-tooltip
            align="center"
        />
        <el-table-column
            align="center"
            fixed="right"
            width="200"
        >
          <template #header>
            <el-input
                v-model="studentSearch"
                size="small"
                placeholder="Search students"
                clearable
                @clear="studentSearch = ''"
                style="width: 160px"
            />
          </template>
          <template #default="scope">
            <el-button
                size="small"
                @click="handleStudentEdit(scope.$index, scope.row)"
            >
              Edit
            </el-button>
            <el-button
                size="small"
                type="danger"
                @click="handleStudentDelete(scope.$index, scope.row)"
            >
              Delete
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="table-header">
        <el-button type="primary" @click="handleStudentAdd">Add Student</el-button>
      </div>

      <div class="pagination-container">
        <el-pagination
            v-model:current-page="studentCurrentPage"
            v-model:page-size="studentPageSize"
            :page-sizes="[5, 10, 20, 50]"
            layout="total, sizes, prev, pager, next, jumper"
            :total="filteredStudentData.length"
            @size-change="handleStudentSizeChange"
            @current-change="handleStudentCurrentChange"
        />
      </div>

      <el-dialog
          v-model="studentDialogVisible"
          title="Edit Student Information"
          width="40%"
      >
        <el-form :model="currentEditStudent" label-width="100px">
          <el-form-item label="ID">
            <el-input v-model="currentEditStudent.id" disabled />
          </el-form-item>
          <el-form-item label="Student ID">
            <el-input v-model="currentEditStudent.student_id" />
          </el-form-item>
          <el-form-item label="Name">
            <el-input v-model="currentEditStudent.name" />
          </el-form-item>
          <el-form-item label="Gender">
            <el-select v-model="currentEditStudent.gender" placeholder="Select gender">
              <el-option label="Male" value="Male" />
              <el-option label="Female" value="Female" />
              <el-option label="Other" value="Other" />
            </el-select>
          </el-form-item>
          <el-form-item label="Birth Date">
            <el-date-picker
                v-model="currentEditStudent.birth_date"
                type="date"
                placeholder="Select date"
                value-format="YYYY-MM-DD"
            />
          </el-form-item>
          <el-form-item label="Phone">
            <el-input v-model="currentEditStudent.contact_phone" />
          </el-form-item>
          <el-form-item label="Email">
            <el-input v-model="currentEditStudent.contact_email" />
          </el-form-item>
          <el-form-item label="Address">
            <el-input v-model="currentEditStudent.address" />
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="studentDialogVisible = false">Cancel</el-button>
            <el-button type="primary" @click="confirmStudentEdit">
              Confirm
            </el-button>
          </span>
        </template>
      </el-dialog>
      <el-dialog
          v-model="addStudentDialogVisible"
          title="Add Student"
          width="40%"
      >
        <el-form :model="newStudent" label-width="100px">
          <el-form-item label="Student ID" required>
            <el-input v-model="newStudent.student_id" />
          </el-form-item>
          <el-form-item label="Name" required>
            <el-input v-model="newStudent.name" />
          </el-form-item>
          <el-form-item label="Gender" required>
            <el-select v-model="newStudent.gender" placeholder="Select gender">
              <el-option label="Male" value="Male" />
              <el-option label="Female" value="Female" />
            </el-select>
          </el-form-item>
          <el-form-item label="Birth Date">
            <el-date-picker
                v-model="newStudent.birth_date"
                type="date"
                placeholder="Select date"
                value-format="YYYY-MM-DD"
            />
          </el-form-item>
          <el-form-item label="Phone">
            <el-input v-model="newStudent.contact_phone" />
          </el-form-item>
          <el-form-item label="Email">
            <el-input v-model="newStudent.contact_email" />
          </el-form-item>
          <el-form-item label="Address">
            <el-input v-model="newStudent.address" />
          </el-form-item>
        </el-form>
        <template #footer>
    <span class="dialog-footer">
      <el-button @click="addStudentDialogVisible = false">Cancel</el-button>
      <el-button type="primary" @click="confirmStudentAdd">Confirm</el-button>
    </span>
        </template>
      </el-dialog>
    </div>

    <!-- School Management Table -->
    <div v-if="activeTab === 'school'" class="table-container">
      <el-table
          :data="schoolPaginatedData"
          style="width: 100%"
          stripe
          border
          highlight-current-row
      >
        <el-table-column
            label="ID"
            prop="id"
            width="80"
            sortable
            align="center"
        />
        <el-table-column
            label="School ID"
            prop="school_id"
            width="120"
            align="center"
        />
        <el-table-column
            label="School Name"
            prop="name"
            width="180"
            align="center"
        />
        <el-table-column
            label="Location"
            prop="location"
            width="150"
            align="center"
        />
        <el-table-column
            label="Description"
            prop="description"
            show-overflow-tooltip
            align="center"
        />
        <el-table-column
            label="Phone"
            prop="contact_phone"
            width="150"
            align="center"
        />
        <el-table-column
            label="Email"
            prop="contact_email"
            width="180"
            align="center"
        />
        <el-table-column
            label="Website"
            prop="website"
            width="180"
            align="center"
        />
        <el-table-column
            align="center"
            fixed="right"
            width="200"
        >
          <template #header>
            <el-input
                v-model="schoolSearch"
                size="small"
                placeholder="Search schools"
                clearable
                @clear="schoolSearch = ''"
                style="width: 160px"
            />
          </template>
          <template #default="scope">
            <el-button
                size="small"
                @click="handleSchoolEdit(scope.$index, scope.row)"
            >
              Edit
            </el-button>
            <el-button
                size="small"
                type="danger"
                @click="handleSchoolDelete(scope.$index, scope.row)"
            >
              Delete
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination-container">
        <el-pagination
            v-model:current-page="schoolCurrentPage"
            v-model:page-size="schoolPageSize"
            :page-sizes="[5, 10, 20, 50]"
            layout="total, sizes, prev, pager, next, jumper"
            :total="filteredSchoolData.length"
            @size-change="handleSchoolSizeChange"
            @current-change="handleSchoolCurrentChange"
        />
      </div>

      <el-dialog
          v-model="schoolDialogVisible"
          title="Edit School Information"
          width="40%"
      >
        <el-form :model="currentEditSchool" label-width="100px">
          <el-form-item label="ID">
            <el-input v-model="currentEditSchool.id" disabled />
          </el-form-item>
          <el-form-item label="School ID">
            <el-input v-model="currentEditSchool.school_id" />
          </el-form-item>
          <el-form-item label="School Name">
            <el-input v-model="currentEditSchool.name" />
          </el-form-item>
          <el-form-item label="Location">
            <el-input v-model="currentEditSchool.location" />
          </el-form-item>
          <el-form-item label="Description">
            <el-input v-model="currentEditSchool.description" type="textarea" />
          </el-form-item>
          <el-form-item label="Phone">
            <el-input v-model="currentEditSchool.contact_phone" />
          </el-form-item>
          <el-form-item label="Email">
            <el-input v-model="currentEditSchool.contact_email" />
          </el-form-item>
          <el-form-item label="Website">
            <el-input v-model="currentEditSchool.website" />
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="schoolDialogVisible = false">Cancel</el-button>
            <el-button type="primary" @click="confirmSchoolEdit">
              Confirm
            </el-button>
          </span>
        </template>
      </el-dialog>
    </div>

    <!-- Application Management Table -->
    <div v-if="activeTab === 'application'" class="table-container">
      <el-table
          :data="applicationPaginatedData"
          style="width: 100%"
          stripe
          border
          highlight-current-row
      >
        <el-table-column label="ID" prop="id" width="80" align="center" />
        <el-table-column label="Student ID" prop="student_id" width="100" align="center" />
        <el-table-column label="School ID" prop="school_id" width="100" align="center" />
        <el-table-column label="Status" prop="status" align="center">
          <template #default="scope">
            <el-tag :type="getStatusType(scope.row.status)">
              {{ scope.row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="Application Date" prop="application_date" width="120" align="center" />
        <el-table-column label="Processed Date" prop="processed_date" width="120" align="center" />
        <el-table-column label="Notes" prop="notes" align="center" show-overflow-tooltip />
        <el-table-column align="center" fixed="right" width="180">
          <template #header>
            <el-input
                v-model="applicationSearch"
                size="small"
                placeholder="Search applications"
                clearable
                @clear="applicationSearch = ''"
                style="width: 150px"
            />
          </template>
          <template #default="scope">
            <el-button
                size="small"
                @click="handleApplicationView(scope.row)"
            >
              View/Edit
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination-container">
        <el-pagination
            v-model:current-page="applicationCurrentPage"
            v-model:page-size="applicationPageSize"
            :page-sizes="[5, 10, 20]"
            layout="total, sizes, prev, pager, next, jumper"
            :total="filteredApplicationData.length"
            @size-change="handleApplicationSizeChange"
            @current-change="handleApplicationCurrentChange"
            background
        />
      </div>

      <el-dialog
          v-model="applicationDialogVisible"
          :title="'Application Details - ID: ' + currentApplication.id"
          width="50%"
      >
        <el-form :model="currentApplication" label-width="100px">
          <el-form-item label="ID">
            <el-input v-model="currentApplication.id" disabled />
          </el-form-item>
          <el-form-item label="Student ID">
            <el-input v-model="currentApplication.student_id" />
          </el-form-item>
          <el-form-item label="School ID">
            <el-input v-model="currentApplication.school_id" />
          </el-form-item>
          <el-form-item label="Status">
            <el-select v-model="currentApplication.status" placeholder="Select status">
              <el-option label="Pending" value="Pending" />
              <el-option label="Processing" value="Processing" />
              <el-option label="Approved" value="Approved" />
              <el-option label="Rejected" value="Rejected" />
            </el-select>
          </el-form-item>
          <el-form-item label="Application Date">
            <el-date-picker
                v-model="currentApplication.application_date"
                type="date"
                placeholder="Select date"
                value-format="YYYY-MM-DD"
            />
          </el-form-item>
          <el-form-item label="Processed Date">
            <el-date-picker
                v-model="currentApplication.processed_date"
                type="date"
                placeholder="Select date"
                value-format="YYYY-MM-DD"
            />
          </el-form-item>
          <el-form-item label="Notes">
            <el-input v-model="currentApplication.notes" type="textarea" />
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="applicationDialogVisible = false">Cancel</el-button>
            <el-button type="primary" @click="saveApplicationChanges">Save</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { computed, ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from 'axios'



interface Student {
  id: number
  student_id: number
  name: string
  gender: string
  birth_date: string
  contact_phone: string
  contact_email: string
  address: string
}


interface School {
  id: number
  school_id: string
  name: string
  location: string
  description: string
  contact_phone: string
  contact_email: string
  website: string
}

interface Application {
  id: number
  student_id: string
  school_id: string
  status: string
  application_date: string
  processed_date: string
  notes: string
}

// Tab state
const activeTab = ref<'student' | 'school' | 'application'>('student')

// Student management related data and methods
const studentData = ref<Student[]>([])

onMounted(() => {
  axios.get<{
    id: number;
    studentId: number;
    name: string;
    gender: string;
    birthDate: string;
    contactPhone: string;
    contactEmail: string;
    address: string;
  }[]>('http://localhost:8082/api/admin/students').then((resp) => {
    studentData.value = resp.data.map((item) => ({
      id: item.id,
      student_id: item.studentId,
      name: item.name,
      gender: item.gender,
      birth_date: item.birthDate,
      contact_phone: item.contactPhone,
      contact_email: item.contactEmail,
      address: item.address
    }))
  }).catch((error) => {
    console.error('Error fetching student data:', error)
  })
})
const studentSearch = ref('')
const filteredStudentData = computed(() => {
  const searchTerm = studentSearch.value.toLowerCase()
  return studentData.value.filter(student =>
      !searchTerm ||
      student.name.toLowerCase().includes(searchTerm) ||
      student.student_id.toString().toLowerCase().includes(searchTerm) || // 添加 toString()
      student.gender.toLowerCase().includes(searchTerm) ||
      student.contact_phone.includes(searchTerm) ||
      student.contact_email.toLowerCase().includes(searchTerm) ||
      student.address.toLowerCase().includes(searchTerm) ||
      student.id.toString().includes(searchTerm)
  )
})

const studentCurrentPage = ref(1)
const studentPageSize = ref(5)
const studentPaginatedData = computed(() => {
  const start = (studentCurrentPage.value - 1) * studentPageSize.value
  return filteredStudentData.value.slice(start, start + studentPageSize.value)
})

const handleStudentSizeChange = (newSize: number) => {
  studentPageSize.value = newSize
  studentCurrentPage.value = 1
}

const handleStudentCurrentChange = (newPage: number) => {
  studentCurrentPage.value = newPage
}

const studentDialogVisible = ref(false)
const currentEditStudentIndex = ref(-1)
const currentEditStudent = reactive({
  id: 0,
  student_id: 0,
  name: '',
  gender: 'MALE', // 设置默认值
  birth_date: '',
  contact_phone: '',
  contact_email: '',
  address: ''
})
const handleStudentEdit = (index: number, row: Student) => {
  currentEditStudentIndex.value = (studentCurrentPage.value - 1) * studentPageSize.value + index
  Object.assign(currentEditStudent, {
    id: row.id,
    student_id: row.student_id,
    name: row.name,
    gender: row.gender,
    birth_date: row.birth_date,
    contact_phone: row.contact_phone,
    contact_email: row.contact_email,
    address: row.address
  })
  studentDialogVisible.value = true
}

const confirmStudentEdit = async () => {
  try {
    // 准备要发送给后端的数据（转换为驼峰式）
    const requestData: ApiStudent = {
      id: currentEditStudent.id,
      studentId: currentEditStudent.student_id,
      name: currentEditStudent.name,
      gender: currentEditStudent.gender,
      birthDate: currentEditStudent.birth_date,
      contactPhone: currentEditStudent.contact_phone,
      contactEmail: currentEditStudent.contact_email,
      address: currentEditStudent.address
    }

    // 发送请求
    const response = await axios.put<ApiStudent>(
        `http://localhost:8082/api/admin/students/${currentEditStudent.id}`,
        requestData
    )

    // 更新前端数据
    const updatedStudent: Student = {
      id: response.data.id,
      student_id: response.data.studentId,
      name: response.data.name,
      gender: response.data.gender,
      birth_date: response.data.birthDate,
      contact_phone: response.data.contactPhone,
      contact_email: response.data.contactEmail,
      address: response.data.address
    }

    studentData.value[currentEditStudentIndex.value] = updatedStudent
    ElMessage.success('学生信息更新成功')
    studentDialogVisible.value = false
  } catch (error) {
    ElMessage.error('更新学生信息失败')
    console.error('Error updating student:', error)
  }
}
// Add student dialog visibility
const addStudentDialogVisible = ref(false)

// New student form data
const newStudent = reactive<Omit<Student, 'id'>>({
  student_id: 0,
  name: '',
  gender: '',
  birth_date: '',
  contact_phone: '',
  contact_email: '',
  address: ''
})

// Open add student dialog
// 1. 确保正确定义了接口类型
interface ApiStudent {
  id?: number
  studentId: number  // 注意这里是驼峰式
  name: string
  gender: string
  birthDate: string | null
  contactPhone: string
  contactEmail: string
  address: string
}

interface UiStudent {
  id: number
  student_id: number  // 前端使用蛇形命名
  name: string
  gender: string
  birth_date: string
  contact_phone: string
  contact_email: string
  address: string
}

// 2. 修改添加学生函数
const handleStudentAdd = () => {
  // 重置表单，设置合理的默认值
  Object.assign(newStudent, {
    student_id: 1001,  // 避免使用0
    name: '',
    gender: 'Male',
    birth_date: '',
    contact_phone: '',
    contact_email: '',
    address: ''
  })
  addStudentDialogVisible.value = true
}

const confirmStudentAdd = async () => {
  try {
    // 数据验证
    if (!newStudent.name.trim()) {
      ElMessage.warning('请输入学生姓名')
      return
    }
    if (newStudent.student_id <= 0) {
      ElMessage.warning('学号必须大于0')
      return
    }

    // 准备请求数据（确保字段名与后端一致）
    const requestData: Omit<ApiStudent, 'id'> = {
      studentId: newStudent.student_id,
      name: newStudent.name.trim(),
      gender: newStudent.gender,
      birthDate: newStudent.birth_date || null,  // 处理空值
      contactPhone: newStudent.contact_phone.trim(),
      contactEmail: newStudent.contact_email.trim(),
      address: newStudent.address.trim()
    }

    console.log('正在提交数据:', JSON.stringify(requestData, null, 2))

    // 发送请求（添加错误处理和headers）
    const response = await axios.post<ApiStudent>(
        'http://localhost:8082/api/admin/students',
        requestData,
        {
          headers: {
            'Content-Type': 'application/json',
            // 如果有身份验证
            'Authorization': `Bearer ${localStorage.getItem('token') || ''}`
          }
        }
    )

    console.log('响应数据:', response.data)

    // 处理响应（确保字段映射正确）
    if (response.data && response.data.id) {
      const addedStudent: UiStudent = {
        id: response.data.id,
        student_id: response.data.studentId,  // 注意字段映射
        name: response.data.name,
        gender: response.data.gender,
        birth_date: response.data.birthDate || '',
        contact_phone: response.data.contactPhone,
        contact_email: response.data.contactEmail,
        address: response.data.address
      }

      // 更新前端数据
      studentData.value.unshift(addedStudent)
      ElMessage.success('学生添加成功')
      addStudentDialogVisible.value = false
      studentCurrentPage.value = 1
    } else {
      throw new Error('响应数据不完整')
    }
  } catch (error: any) {
    console.error('完整错误信息:', error)

    // 更详细的错误处理
    let errorMessage = '添加学生失败'
    if (error.response) {
      // 处理后端返回的验证错误
      if (error.response.data?.message) {
        errorMessage += `: ${error.response.data.message}`
      } else if (error.response.data?.errors) {
        errorMessage += `: ${JSON.stringify(error.response.data.errors)}`
      }
    } else if (error.request) {
      errorMessage = '请求未收到响应，请检查网络'
    }

    ElMessage.error(errorMessage)
  }
}

const handleStudentDelete = (index: number, row: Student) => {
  const globalIndex = (studentCurrentPage.value - 1) * studentPageSize.value + index;

  ElMessageBox.confirm(
      `Are you sure to delete student "${row.name}"?`,
      'Warning',
      {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning',
      }
  ).then(async () => {
    try {
      // First delete backend data
      await axios.delete(`http://localhost:8082/api/admin/students/${row.id}`);

      // Then delete frontend data
      studentData.value.splice(globalIndex, 1);

      if (studentPaginatedData.value.length === 0 && studentCurrentPage.value > 1) {
        studentCurrentPage.value -= 1;
      }
      ElMessage.success('Deleted successfully');
    } catch (error) {
      ElMessage.error('Failed to delete student');
      console.error('Error deleting student:', error);
    }
  }).catch(() => {
    ElMessage.info('Deletion canceled');
  });
}

// School management related data and methods
const schoolData = ref<School[]>([
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
    location: 'Hangzhou, Zhejiang',
    description: 'Top comprehensive university in China',
    contact_phone: '0571-87951006',
    contact_email: 'zsc@zju.edu.cn',
    website: 'https://www.zju.edu.cn'
  },
  {
    id: 6,
    school_id: 'SC006',
    name: 'Nanjing University',
    location: 'Nanjing, Jiangsu',
    description: 'Top comprehensive university in China',
    contact_phone: '025-83593186',
    contact_email: 'bkzs@nju.edu.cn',
    website: 'https://www.nju.edu.cn'
  }
])

const schoolSearch = ref('')
const filteredSchoolData = computed(() => {
  const searchTerm = schoolSearch.value.toLowerCase()
  return schoolData.value.filter(school =>
      !searchTerm ||
      school.name.toLowerCase().includes(searchTerm) ||
      school.school_id.toLowerCase().includes(searchTerm) ||
      school.location.toLowerCase().includes(searchTerm) ||
      school.contact_phone.includes(searchTerm) ||
      school.contact_email.toLowerCase().includes(searchTerm) ||
      school.website.toLowerCase().includes(searchTerm) ||
      school.id.toString().includes(searchTerm)
  )
})

const schoolCurrentPage = ref(1)
const schoolPageSize = ref(5)
const schoolPaginatedData = computed(() => {
  const start = (schoolCurrentPage.value - 1) * schoolPageSize.value
  return filteredSchoolData.value.slice(start, start + schoolPageSize.value)
})

const handleSchoolSizeChange = (newSize: number) => {
  schoolPageSize.value = newSize
  schoolCurrentPage.value = 1
}

const handleSchoolCurrentChange = (newPage: number) => {
  schoolCurrentPage.value = newPage
}

const schoolDialogVisible = ref(false)
const currentEditSchoolIndex = ref(-1)
const currentEditSchool = reactive<School>({
  id: 0,
  school_id: '',
  name: '',
  location: '',
  description: '',
  contact_phone: '',
  contact_email: '',
  website: ''
})

const handleSchoolEdit = (index: number, row: School) => {
  currentEditSchoolIndex.value = (schoolCurrentPage.value - 1) * schoolPageSize.value + index
  Object.assign(currentEditSchool, row)
  schoolDialogVisible.value = true
}

const confirmSchoolEdit = () => {
  if (currentEditSchoolIndex.value >= 0) {
    schoolData.value[currentEditSchoolIndex.value] = { ...currentEditSchool }
    ElMessage.success('School information updated successfully')
    schoolDialogVisible.value = false
  }
}

const handleSchoolDelete = (index: number, row: School) => {
  const globalIndex = (schoolCurrentPage.value - 1) * schoolPageSize.value + index

  ElMessageBox.confirm(
      `Are you sure to delete school "${row.name}"?`,
      'Warning',
      {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning',
      }
  ).then(() => {
    schoolData.value.splice(globalIndex, 1)
    if (schoolPaginatedData.value.length === 0 && schoolCurrentPage.value > 1) {
      schoolCurrentPage.value -= 1
    }
    ElMessage.success('Deleted successfully')
  }).catch(() => {
    ElMessage.info('Deletion canceled')
  })
}

// Application management related data and methods
const applicationData = ref<Application[]>([
  {
    id: 1,
    student_id: 'S001',
    school_id: 'SC001',
    status: 'Pending',
    application_date: '2023-05-01',
    processed_date: '',
    notes: 'Excellent grades'
  },
  {
    id: 2,
    student_id: 'S002',
    school_id: 'SC002',
    status: 'Processing',
    application_date: '2023-05-02',
    processed_date: '',
    notes: 'Special talent'
  },
  {
    id: 3,
    student_id: 'S003',
    school_id: 'SC003',
    status: 'Approved',
    application_date: '2023-05-03',
    processed_date: '2023-05-10',
    notes: 'Competition award'
  },
  {
    id: 4,
    student_id: 'S004',
    school_id: 'SC004',
    status: 'Rejected',
    application_date: '2023-05-04',
    processed_date: '2023-05-12',
    notes: 'Incomplete materials'
  },
  {
    id: 5,
    student_id: 'S005',
    school_id: 'SC005',
    status: 'Pending',
    application_date: '2023-05-05',
    processed_date: '',
    notes: 'Recommendation letter pending'
  },
  {
    id: 6,
    student_id: 'S006',
    school_id: 'SC006',
    status: 'Processing',
    application_date: '2023-05-06',
    processed_date: '',
    notes: 'Interview scheduling'
  }
])

const applicationSearch = ref('')
const filteredApplicationData = computed(() => {
  return applicationData.value.filter(data =>
      !applicationSearch.value ||
      data.student_id.toLowerCase().includes(applicationSearch.value.toLowerCase()) ||
      data.school_id.toLowerCase().includes(applicationSearch.value.toLowerCase()) ||
      data.status.toLowerCase().includes(applicationSearch.value.toLowerCase())
  )
})

const applicationCurrentPage = ref(1)
const applicationPageSize = ref(10)
const applicationPaginatedData = computed(() => {
  const start = (applicationCurrentPage.value - 1) * applicationPageSize.value
  return filteredApplicationData.value.slice(start, start + applicationPageSize.value)
})

const handleApplicationSizeChange = (newSize: number) => {
  applicationPageSize.value = newSize
  applicationCurrentPage.value = 1
}

const handleApplicationCurrentChange = (newPage: number) => {
  applicationCurrentPage.value = newPage
}

const applicationDialogVisible = ref(false)
const currentApplication = reactive<Application>({
  id: 0,
  student_id: '',
  school_id: '',
  status: 'Pending',
  application_date: '',
  processed_date: '',
  notes: ''
})

const handleApplicationView = (row: Application) => {
  Object.assign(currentApplication, row)
  applicationDialogVisible.value = true
}

const saveApplicationChanges = () => {
  const index = applicationData.value.findIndex(item => item.id === currentApplication.id)
  if (index !== -1) {
    applicationData.value[index] = { ...currentApplication }
    applicationData.value = [...applicationData.value]
    ElMessage.success('Application information updated successfully')
  }
  applicationDialogVisible.value = false
}

const getStatusType = (status: string) => {
  switch (status) {
    case 'Approved': return 'success'
    case 'Processing': return 'warning'
    case 'Rejected': return 'danger'
    default: return 'info'
  }
}
</script>

<style scoped>
.app-container {
  padding: 20px;
}

.header-buttons {
  margin-bottom: 20px;
  display: flex;
  gap: 10px;
}

.active-button {
  background-color: var(--el-color-primary);
  color: white;
}

.table-container {
  background: #fff;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.el-table {
  margin-top: 20px;
}

:deep(.el-table .cell) {
  text-align: center;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}

:deep(.el-table__body tr:hover > td) {
  background-color: #f5f7fa !important;
}

.el-tag {
  margin: 2px;
}
.table-header {
  margin-bottom: 20px;
  margin-left: 20px;
  display: flex;
  justify-content: flex-end;
}
</style>