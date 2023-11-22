<template>
  <div style="display: flex;">
    <div class="bg-white" style="flex:2;margin-right: 10px;padding: 10px;">
      <el-scrollbar height="560px" style="margin-top: 10px;">
        <el-tree
          ref="treeRef"
          :data="buildTree"
          :props="defaultProps"
          :default-expand-all="true"
          show-checkbox
          check-strictly
          @check="handleCheck"
        />
      </el-scrollbar>
    </div>
    <div class="table-box" style="flex: 8">
      <ProTable
        ref="proTable"
        title="用户列表"
        :columns="columns"
        :requestApi="getTableList"
        :initParam="initParam"
        :dataCallback="dataCallback"
      >
        <!-- 表格 header 按钮 -->
        <template #tableHeader>
          <el-button type="primary" :icon="Plus" @click="openDrawer('node','新增')">
            <span>新增节点</span>
          </el-button>
          <el-button type="warning" :icon="EditPen" @click="openDrawer('node','编辑')">
            <span>编辑节点</span>
          </el-button>
          <el-button type="danger" :icon="Delete" @click="deleteTypeData">
            <span>删除节点</span>
          </el-button>
        </template>

        <!-- 表格操作 -->
        <template #operation="scope">
          <el-button
            size="small"
            link
            :icon="View"
            @click="openDrawer('bed','查看',scope)"
          >
            查看
          </el-button>
          <el-button
            size="small"
            link
            :icon="EditPen"
            @click="openDrawer('bed','编辑',scope)"
          >
            编辑
          </el-button>
          <el-popconfirm
            title="Are you sure to delete this?"
            @confirm="deleteBedData(scope)"
            confirm-button-type="danger"
          >
            <template #reference>
              <el-button size="small" link :icon="Delete">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </ProTable>
      <buildDialog @operateNode="operateNode" ref="buildingDialogRef" />
      <floorDialog @operateNode="operateNode" ref="floorDialogRef" />
      <roomDialog @operateNode="operateNode" ref="roomDialogRef" />
      <bedDialog @operateNode="operateNode" ref="bedDialogRef" />
    </div>
  </div>
</template>

<script setup lang="ts" name="useProTable">
import { ref, reactive, onMounted } from "vue";
import { ElMessage } from "element-plus";
import buildDialog from "./dialog/build.vue";
import floorDialog from "./dialog/floor.vue";
import roomDialog from "./dialog/room.vue";
import bedDialog from "./dialog/bed.vue";
import { ColumnProps } from "@/components/ProTable/interface";
import ProTable from "@/components/ProTable/index.vue";
import { Delete, EditPen, View, Plus } from "@element-plus/icons-vue";
import {
  addBed,
  addBuilding, addFloor, addRoom,
  deleteBed,
  deleteNode, editBed,
  editBuilding, editFloor, editRoom,
  getNoBedTree,
  IBedFlagList,
  pageBedByKey
} from "@/apis/build";

// 获取 ProTable 元素，调用其获取刷新数据方法（还能获取到当前查询参数，方便导出携带参数）
const proTable = ref();
const initParam = reactive({});

const treeRef = ref<any>();
const ICheckedNode = ref<any>({});
const buildTree = ref<any>([]);
const defaultProps = {
  children: "childrenList",
  label: "name"
};
// 复选框被点击
const handleCheck = (checkedNode: any, checkedNodeDetail: any) => {
  if (checkedNodeDetail.checkedNodes.length > 0) {
    ICheckedNode.value = checkedNode;
  } else {
    ICheckedNode.value = { id: -1, level: -1 };
  }
  getTableListHandle(1, 10);
  // 取消选中其他节点
  treeRef.value.getCheckedNodes().forEach((node: any) => {
    if (node.onlyMark !== ICheckedNode.value.onlyMark) {
      treeRef.value.setChecked(node, false);
    }
  });
};

// 页面渲染时
onMounted(() => {
  getBuildTreeHandle();
});

// dataCallback 是对于返回的表格数据做处理，如果你后台返回的数据不是 list && total && pageNum && pageSize 这些字段，那么你可以在这里进行处理成这些字段
// 或者直接去 hooks/useTable.ts 文件中把字段改为你后端对应的就行
const dataCallback = (data: any) => {
  return {
    list: data.list,
    total: data.total,
    pageNum: data.pageNum,
    pageSize: data.pageSize
  };
};

// 如果你想在请求之前对当前请求参数做一些操作，可以自定义如下函数：params 为当前所有的请求参数（包括分页），最后返回请求列表接口
// 默认不做操作就直接在 ProTable 组件上绑定	:requestApi="getUserList"
const getTableList = (params: any) => {
  params = addSearchField(params);
  let newParams = JSON.parse(JSON.stringify(params));
  return pageBedByKey(newParams);
};

// 重新获取表格数据
const getTableListHandle = (pageNum: number, pageSize: number) => {
  let params: any = {};
  params = addSearchField(params);
  proTable.value.getTableList({
    pageNum: pageNum,
    pageSize: pageSize,
    buildId: params.buildId,
    floorId: params.floorId,
    roomId: params.roomId,
    bedFlag: proTable.value.searchParam.bedFlag
  });
};

// 重新获取楼栋树
const getBuildTreeHandle = async () => {
  ICheckedNode.value = { id: -1, level: -1 };
  const res: any = await getNoBedTree();
  buildTree.value = res.data;
  getTableListHandle(1, 10);
};

// 新增搜索字段
const addSearchField = (params: any) => {
  if (ICheckedNode.value.id !== -1) {
    if (ICheckedNode.value.level === 1) {
      params.buildId = ICheckedNode.value.id;
      params.floorId = null;
      params.roomId = null;
    } else if (ICheckedNode.value.level === 2) {
      params.buildId = null;
      params.floorId = ICheckedNode.value.id;
      params.roomId = null;
    } else if (ICheckedNode.value.level === 3) {
      params.buildId = null;
      params.floorId = null;
      params.roomId = ICheckedNode.value.id;
    }
  } else {
    params.buildId = null;
    params.floorId = null;
    params.roomId = null;
  }
  return params;
};

// 删除节点
const deleteTypeData = async () => {
  if ((Object.keys(ICheckedNode.value).length === 0 || ICheckedNode.value.id === -1)) {
    ElMessage.warning("请选择节点");
    return;
  }
  const res: any = await deleteNode({
    id: ICheckedNode.value.id,
    mark: ICheckedNode.value.level === 1 ? "楼栋" : ICheckedNode.value.level === 2 ? "楼层" : ICheckedNode.value.level === 3 ? "房间" : ""
  });
  if (res.code === 200) {
    ElMessage.success(res.msg);
    await getBuildTreeHandle();
  } else {
    ElMessage.error(res.msg);
  }
};

// 删除床位
const deleteBedData = async (params: any) => {
  const res: any = await deleteBed({
    bedId: params.row.id
  });
  if (res.code === 200) {
    ElMessage.success(res.msg);
    proTable.value.getTableList();
  } else {
    ElMessage.error(res.msg);
  }
};

// 打开服务 drawer(新增、查看、编辑)
const buildingDialogRef = ref();
const floorDialogRef = ref();
const roomDialogRef = ref();
const bedDialogRef = ref();
const openDrawer = (type: string, title: string, rowData: any = {}) => {
  if (type === "node") {
    // 编辑时必须选择节点
    if (title === "编辑" && (Object.keys(ICheckedNode.value).length === 0 || ICheckedNode.value.id === -1)) {
      ElMessage.warning("请选择节点");
      return;
    }
    // 操作节点
    if (title === "新增") {
      const parentNode = treeRef.value.getCheckedNodes()[0];
      if (ICheckedNode.value.level === -1) {// 楼栋
        const params = {
          title: title + "楼栋",
          rowData: {},
          isView: false,
          api: addBuilding
        };
        buildingDialogRef.value.buildingAcceptParams(params);
      } else if (ICheckedNode.value.level === 1) {// 楼层
        const params = {
          title: title + "楼层",
          rowData: {
            buildingId: parentNode.buildId,
            floorLimit: parentNode.floorLimit
          },
          isView: false,
          api: addFloor
        };
        floorDialogRef.value.floorAcceptParams(params);
      } else if (ICheckedNode.value.level === 2) {// 房间
        const params = {
          title: title + "房间",
          rowData: {
            floorId: parentNode.floorId,
            roomLimit: parentNode.roomLimit
          },
          isView: false,
          api: addRoom
        };
        roomDialogRef.value.roomAcceptParams(params);
      } else if (ICheckedNode.value.level === 3) {// 床位
        const params = {
          title: title + "床位",
          rowData: {
            roomId: parentNode.roomId,
            bedLimit: parentNode.bedLimit
          },
          isView: false,
          api: addBed
        };
        bedDialogRef.value.bedAcceptParams(params);
      }
    } else if (title === "编辑") {
      if (ICheckedNode.value.level === 1) {// 楼栋
        const params = {
          title: title + "楼栋",
          rowData: { ...ICheckedNode.value },
          isView: false,
          api: editBuilding
        };
        buildingDialogRef.value.buildingAcceptParams(params);
      } else if (ICheckedNode.value.level === 2) {// 楼层
        const params = {
          title: title + "楼层",
          rowData: { ...ICheckedNode.value },
          isView: false,
          api: editFloor
        };
        floorDialogRef.value.floorAcceptParams(params);
      } else if (ICheckedNode.value.level === 3) {// 房间
        const params = {
          title: title + "房间",
          rowData: { ...ICheckedNode.value },
          isView: false,
          api: editRoom
        };
        roomDialogRef.value.roomAcceptParams(params);
      }
    }
  } else if (type === "bed") {// 床位
    const params = {
      title,
      rowData: { ...rowData.row },
      isView: title === "查看",
      api: title === "新增" ? addBed : title === "编辑" ? editBed : "",
    };
    bedDialogRef.value.bedAcceptParams(params);
  }
};

// 操作完节点后调用
const operateNode = () => {
  getBuildTreeHandle();
};

// 表格配置项
const columns: ColumnProps<any>[] = [
  { prop: "rank", label: "序号", width: 55 },
  { prop: "name", label: "床位名称" },
  {
    enum: IBedFlagList,
    prop: "bedFlag",
    label: "床位状态",
    search: { el: "select", props: { filterable: false } }
  },
  { prop: "operation", label: "操作", width: 220 }
];
</script>

<style lang="scss" scoped>
:deep(.el-radio__input) {
  display: none !important;
}

:deep(.el-radio__label) {
  font-size: 1em;
  width: 248px;
  padding: 5px 0 5px 10px;
  transition: all 0.2s;

  &:hover {
    background-color: #eeeeee;
  }
}

.el-radio-group {
  display: flex;
  flex-wrap: wrap;

  .el-radio {
    flex: 1;
    margin-right: 100%;
  }
}
</style>
